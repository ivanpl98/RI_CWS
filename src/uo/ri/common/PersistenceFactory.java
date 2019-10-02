package uo.ri.common;

import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.impl.MechanicGatewayImpl;

import java.sql.Connection;

public class PersistenceFactory {

    public static MechanicGateway getMechanicGateway(Connection connection) {
        return new MechanicGatewayImpl(connection);
    }

}
