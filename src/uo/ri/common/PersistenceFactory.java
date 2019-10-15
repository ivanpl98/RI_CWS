package uo.ri.common;

import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;

public class PersistenceFactory {

    public static MechanicGateway getMechanicGateway(Connection connection) {
        return new MechanicGateway(connection);
    }

}
