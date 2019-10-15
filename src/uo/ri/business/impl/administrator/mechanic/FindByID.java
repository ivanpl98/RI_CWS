package uo.ri.business.impl.administrator.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.common.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class FindByID {

    Long id;

    public FindByID(Long id) {
        this.id = id;
    }

    public MechanicDto excecute() {
        Connection conn = null;
        try {
            conn = Jdbc.getConnection();
            MechanicGateway mg = PersistenceFactory.getMechanicGateway(conn);
            return mg.findById(this.id);
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(conn);
        }
    }

}
