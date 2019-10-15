package uo.ri.business.impl.administrator.mechanic;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.common.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteMechanic {

    Long id;

    public DeleteMechanic(Long id) {
        this.id = id;
    }


    public void execute() throws BusinessException {
        Connection conn = null;
        try {
            conn = Jdbc.getConnection();
            conn.setAutoCommit(false);
            MechanicGateway mg = PersistenceFactory.getMechanicGateway(conn);
            if (mg.findById(this.id) == null) {
                conn.rollback();
                throw new BusinessException("The id: " + this.id + " does not exists in the database");
            }
            if (!mg.isDeletable(this.id)){
                conn.rollback();
                throw new BusinessException("The id: " + this.id + " cannot be deleted from the database");
            }
            mg.delete(this.id);
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
