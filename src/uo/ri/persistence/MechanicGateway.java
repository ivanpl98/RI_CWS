package uo.ri.persistence;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.Conf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MechanicGateway extends AbstractGateway implements Gateway<MechanicDto> {


    public MechanicGateway(Connection conn) {
        super(conn);
    }


    @Override
    public void add(MechanicDto mechanic) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_ADD_MECHANIC"));

        pst.setString(1, mechanic.dni);
        pst.setString(2, mechanic.name);
        pst.setString(3, mechanic.surname);

        pst.executeUpdate();

        Jdbc.close(pst);

    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_DELETE_MECHANIC"));

        pst.setLong(1, id);


        pst.executeUpdate();

        Jdbc.close(pst);

    }

    @Override
    public void update(MechanicDto mechanic) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_UPDATE_MECHANIC"));


        pst.setString(1, mechanic.dni);
        pst.setString(2, mechanic.name);
        pst.setString(3, mechanic.surname);

        pst.setLong(4, mechanic.id);

        pst.executeUpdate();

        Jdbc.close(pst);

    }

    @Override
    public List<MechanicDto> findAll() throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<MechanicDto> mechanics = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_LIST_MECHANICS"));
        mechanics = resultSetToList(pst.executeQuery());

        Jdbc.close(rs, pst);


        return mechanics;
    }

    public MechanicDto findById(Long id) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        MechanicDto mechanic = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_MECHANIC_ID"));

        rs = pst.executeQuery();

        while (rs.next()) {
            MechanicDto mec = resultSetToMechanic(rs);
        }

        Jdbc.close(rs, pst);

        return mechanic;

    }

    public MechanicDto findByDni(MechanicDto mec) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        MechanicDto mechanic = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_FIND_MECHANIC_DNI"));

        pst.setString(1, mec.dni);

        rs = pst.executeQuery();

        while (rs.next()) {
            MechanicDto m = resultSetToMechanic(rs);
        }

        Jdbc.close(rs, pst);

        return mechanic;

    }

    /**
     public List<MechanicDto> findActiveMechanics() throws SQLException {
     PreparedStatement pst = null;
     ResultSet rs = null;
     List<MechanicDto> mechanics = null;

     pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_LIST_ACTIVE_MECHANICS"));
     mechanics = resultSetToList(pst.executeQuery());

     Jdbc.close(rs, pst);

     return mechanics;
     }
     */

    /**
     * public boolean isDeletable(Long id) {
     * PreparedStatement pst = null;
     * ResultSet rs = null;
     * <p>
     * try {
     * pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_IS_MECHANIC_DELETABLE"));
     * pst.setLong(1, id);
     * <p>
     * rs = pst.executeQuery();
     * while (rs.next()) {
     * if (rs.getInt("c") != 0)
     * return false;
     * <p>
     * }
     * <p>
     * } catch (SQLException sqle) {
     * throw new RuntimeException(sqle);
     * } finally {
     * Jdbc.close(rs, pst);
     * }
     * <p>
     * return true;
     * }
     */

    private List<MechanicDto> resultSetToList(ResultSet rs) throws SQLException {
        List<MechanicDto> mechanics = new ArrayList<MechanicDto>();

        while (rs.next()) {
            MechanicDto mec = resultSetToMechanic(rs);

            mechanics.add(mec);
        }

        return mechanics;
    }

    private MechanicDto resultSetToMechanic(ResultSet rs) throws SQLException {
        MechanicDto mec = new MechanicDto();
        mec.id = rs.getLong("id");
        mec.dni = rs.getString("dni");
        mec.name = rs.getString("name");
        mec.surname = rs.getString("surname");
        return mec;
    }
}
