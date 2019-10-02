package uo.ri.persistence;

import java.sql.Connection;

public class AbstractGateway {

    protected Connection conn;

    public AbstractGateway() {

    }

    public AbstractGateway(Connection conn) {
        this.conn = conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }


}
