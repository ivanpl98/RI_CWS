package uo.ri.business.impl.administrator.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.common.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class AddMechanic {

    MechanicDto mechanic;

    public AddMechanic(MechanicDto mechanic) {
        this.mechanic = mechanic;
    }


    public void execute() throws BusinessException {
        Connection conn = null;
        try {
            conn = Jdbc.getConnection();
            conn.setAutoCommit(false);
            MechanicGateway mg = PersistenceFactory.getMechanicGateway(conn);
            if (mg.findByDni(this.mechanic) != null)
                throw new BusinessException("The dni: " + this.mechanic.dni + " already exists in the database");
            mg.add(this.mechanic);
            conn.commit();
        } catch (SQLException sqle) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(sqle);
            }
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(conn);
        }
    }

}
