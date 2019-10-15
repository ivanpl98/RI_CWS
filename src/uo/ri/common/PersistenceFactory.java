package uo.ri.common;

import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.WorkorderGateway;

import java.sql.Connection;

public class PersistenceFactory {

    public static MechanicGateway getMechanicGateway(Connection connection) {
        return new MechanicGateway(connection);
    }

    public static WorkorderGateway getWorkorderGateway(Connection conn) {
        return new WorkorderGateway(conn);
    }

}
