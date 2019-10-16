package uo.ri.persistence;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.Conf;
import uo.ri.common.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkorderGateway extends AbstractGateway implements Gateway<WorkOrderDto> {

    public WorkorderGateway(Connection conn) {
        super(conn);
    }

    @Override
    public void add(WorkOrderDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_ADD_WORKORDER"));

        setPreparedStatementValues(pst, obj);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_DELETE_WORKORDER"));

        pst.setLong(1, id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void update(WorkOrderDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER"));

        setPreparedStatementValues(pst, obj);
        pst.setLong(8, obj.id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public List<WorkOrderDto> findAll() throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<WorkOrderDto> workorders = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_LIST_WORKORDERS"));
        workorders = resultSetToList(pst.executeQuery());

        Jdbc.close(rs, pst);


        return workorders;
    }

    public List<WorkOrderDto> findActive() throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<WorkOrderDto> workorders = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_LIST_ACTIVE_WORKORDERS"));
        workorders = resultSetToList(pst.executeQuery());

        Jdbc.close(rs, pst);


        return workorders;
    }


    private void setPreparedStatementValues(PreparedStatement pst, WorkOrderDto obj) throws SQLException {
        pst.setDouble(1, obj.total);
        pst.setDate(2, Util.convertToSqlDate(obj.date));
        pst.setString(3, obj.description);
        pst.setString(4, obj.status);
        pst.setLong(4, obj.invoiceId);
        pst.setLong(6, obj.mechanicId);
        pst.setLong(7, obj.vehicleId);
    }

    private List<WorkOrderDto> resultSetToList(ResultSet rs) throws SQLException {
        List<WorkOrderDto> workorders = new ArrayList<WorkOrderDto>();

        while (rs.next())
            workorders.add(resultSetToWorkoder(rs));

        rs.close();

        return workorders;
    }

    private WorkOrderDto resultSetToWorkoder(ResultSet rs) throws SQLException {
        WorkOrderDto work = new WorkOrderDto();
        work.id = rs.getLong("id");
        work.total = rs.getDouble("amount");
        work.date = rs.getDate("date");
        work.description = rs.getString("description");
        work.status = rs.getString("status");
        work.invoiceId = rs.getLong("invoice_id");
        work.mechanicId = rs.getLong("mechanic_id");
        work.vehicleId = rs.getLong("vehicle_id");
        return work;
    }
}
