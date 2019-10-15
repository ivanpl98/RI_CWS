package uo.ri.business.impl.administrator.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.WorkorderGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FindActive {

    public FindActive() {

    }

    public List<MechanicDto> execute() {
        Connection conn = null;
        try {
            conn = Jdbc.getConnection();
            MechanicGateway mg = PersistenceFactory.getMechanicGateway(conn);
            WorkorderGateway wg = PersistenceFactory.getWorkorderGateway(conn);
            List<Long> work = wg.findActive().stream().map(x -> x.mechanicId).collect(Collectors.toList());
            List<MechanicDto> mec = mg.findAll().stream().filter(x -> work.contains(x.id)).collect(Collectors.toList());
            return mec;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(conn);
        }
    }
}
