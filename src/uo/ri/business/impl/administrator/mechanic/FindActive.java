package uo.ri.business.impl.administrator.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FindActive {

    public FindActive() {

    }

    public List<MechanicDto> execute() {
        Connection conn = null;
        try {
            conn = Jdbc.getConnection();
            MechanicGateway mg = PersistenceFactory.getMechanicGateway(conn);
            return mg.findActiveMechanics();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(conn);
        }
    }
}
