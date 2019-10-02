package uo.ri.persistence.impl;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.Conf;
import uo.ri.persistence.AbstractGateway;
import uo.ri.persistence.MechanicGateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MechanicGatewayImpl extends AbstractGateway implements MechanicGateway {

    public MechanicGatewayImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void addMechanic(MechanicDto mechanic) {

        PreparedStatement pst = null;

        try {
            pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_ADD_MECHANIC"));

            pst.setString(1, mechanic.dni);
            pst.setString(2, mechanic.name);
            pst.setString(3, mechanic.surname);

            pst.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(pst);
        }

    }

    @Override
    public void deleteMechanic(Long id) {
        PreparedStatement pst = null;

        try {
            pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_DELETE_MECHANIC"));

            pst.setLong(1, id);


            pst.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(pst);
        }
    }

    @Override
    public void updateMechanic(MechanicDto mechanic) {
        PreparedStatement pst = null;

        try {
            pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_UPDATE_MECHANIC"));

            pst.setString(1, mechanic.dni);
            pst.setString(2, mechanic.name);
            pst.setString(3, mechanic.surname);

            pst.setLong(4, mechanic.id);

            pst.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(pst);
        }
    }

    @Override
    public List<MechanicDto> listMechanic() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<MechanicDto> mechanics = null;

        try {
            pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_LIST_MECHANICS"));
            mechanics = new ArrayList<MechanicDto>();

            rs = pst.executeQuery();

            while (rs.next()) {
                MechanicDto mec = new MechanicDto();
                mec.id = rs.getLong("id");
                mec.dni = rs.getString("dni");
                mec.name = rs.getString("name");
                mec.surname = rs.getString("surname");

                mechanics.add(mec);
            }

        } catch (SQLException sqle) {

            throw new RuntimeException(sqle);

        } finally {

            Jdbc.close(rs, pst);

        }

        return mechanics;

    }

    @Override
    public boolean isDeletable(Long id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_IS_MECHANIC_DELETABLE"));
            pst.setLong(1, id);

            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("c") != 0)
                    return false;

            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(rs, pst);
        }

        return true;
    }

    @Override
    public boolean existsId(Long id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_EXISTS_ID_MECHANIC"));
            pst.setLong(1, id);

            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("c") != 0)
                    return true;

            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(rs, pst);
        }

        return false;
    }

    @Override
    public boolean existsDni(String dni) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = this.conn.prepareStatement(Conf.getInstance().getProperty("SQL_EXISTS_DNI_MECHANIC"));
            pst.setString(1, dni);

            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("c") != 0)
                    return true;

            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            Jdbc.close(rs, pst);
        }

        return false;
    }
}
