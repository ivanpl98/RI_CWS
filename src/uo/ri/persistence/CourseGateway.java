package uo.ri.persistence;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.Conf;
import uo.ri.common.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseGateway extends AbstractGateway implements Gateway<CourseDto> {

    public CourseGateway(Connection conn) {
        super(conn);
    }

    @Override
    public void add(CourseDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_ADD_COURSE"));

        configurePStatement(pst, obj);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_DELETE_COURSE"));

        pst.setLong(1, id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void update(CourseDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_UPDATE_COURSE"));

        configurePStatement(pst, obj);
        pst.setLong(7, obj.id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public List<CourseDto> findAll() throws SQLException {
        PreparedStatement pst = null;
        List<CourseDto> cs = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_LIST_COURSES"));

        cs = resultSetToList(pst.executeQuery());

        pst.executeUpdate();

        Jdbc.close(pst);

        return cs;
    }

    private void configurePStatement(PreparedStatement pst,
                                     CourseDto obj) throws SQLException {
        pst.setString(1, obj.code);
        pst.setString(2, obj.name);
        pst.setString(3, obj.description);
        pst.setInt(4, obj.hours);
        pst.setDate(5, Util.convertToSqlDate(obj.startDate));
        pst.setDate(6, Util.convertToSqlDate(obj.endDate));
    }

    private List<CourseDto> resultSetToList(ResultSet rs) throws SQLException {
        List<CourseDto> cs = new ArrayList<CourseDto>();
        while (rs.next())
            cs.add(resultSetToCourse(rs));
        rs.close();
        return cs;
    }

    private CourseDto resultSetToCourse(ResultSet rs) throws SQLException {
        CourseDto c = new CourseDto();
        c.id = rs.getLong("id");
        c.name = rs.getString("name");
        c.description = rs.getString("description");
        c.hours = rs.getInt("hours");
        c.startDate = Util.convertFromSqlDate(rs.getDate("startDate"));
        c.endDate = Util.convertFromSqlDate(rs.getDate("endDate"));
        return c;
    }
}
