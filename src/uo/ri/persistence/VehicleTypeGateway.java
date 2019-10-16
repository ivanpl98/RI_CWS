package uo.ri.persistence;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.common.Conf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleTypeGateway extends AbstractGateway implements Gateway<VehicleTypeDto> {

    public VehicleTypeGateway(Connection conn) {
        super(conn);
    }

    @Override
    public void add(VehicleTypeDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_ADD_VEHICLETYPE"));

        pst.setString(1, obj.name);
        pst.setInt(2, obj.minTrainigHours);
        pst.setDouble(3, obj.pricePerHour);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_DELETE_VEHICLETYPE"));

        pst.setLong(1, id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void update(VehicleTypeDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_UPDATE_VEHICLETYPE"));

        pst.setString(1, obj.name);
        pst.setInt(2, obj.minTrainigHours);
        pst.setDouble(3, obj.pricePerHour);
        pst.setLong(4, obj.id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public List<VehicleTypeDto> findAll() throws SQLException {
        PreparedStatement pst = null;
        List<VehicleTypeDto> vts = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_LIST_VEHICLETYPES"));
        vts = resultSetToList(pst.executeQuery());

        Jdbc.close(pst);


        return vts;
    }

    private List<VehicleTypeDto> resultSetToList(ResultSet rs) throws SQLException {
        List<VehicleTypeDto> vts = new ArrayList<VehicleTypeDto>();
        while (rs.next())
            vts.add(resultSetToVehicleType(rs));
        rs.close();
        return vts;
    }

    private VehicleTypeDto resultSetToVehicleType(ResultSet rs) throws SQLException {
        VehicleTypeDto v = new VehicleTypeDto();
        v.id = rs.getLong("id");
        v.name = rs.getString("name");
        v.minTrainigHours = rs.getInt("minTrainingHours");
        v.pricePerHour = rs.getDouble("pricePerHour");
        return v;
    }
}
