package uo.ri.persistence;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.Conf;
import uo.ri.common.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceGateway extends AbstractGateway implements Gateway<InvoiceDto> {

    @Override
    public void add(InvoiceDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_ADD_INVOICE"));

        configurePStatement(pst, obj);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_DELETE_INVOICE"));

        pst.setLong(1, id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public void update(InvoiceDto obj) throws SQLException {
        PreparedStatement pst = null;

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_UPDATE_INVOICE"));

        configurePStatement(pst, obj);
        pst.setLong(6, obj.id);

        pst.executeUpdate();

        Jdbc.close(pst);
    }

    @Override
    public List<InvoiceDto> findAll() throws SQLException {
        PreparedStatement pst = null;
        List<InvoiceDto> is = new ArrayList<InvoiceDto>();

        pst = this.conn.prepareStatement(Conf.getInstance().getProperty(
                "SQL_LIST_INVOICES"));

        is = resultSetToList(pst.executeQuery());

        Jdbc.close(pst);

        return is;
    }

    private void configurePStatement(PreparedStatement pst,
                                     InvoiceDto invoice) throws SQLException {
        pst.setLong(1, invoice.number);
        pst.setDate(2, Util.convertToSqlDate(invoice.date));
        pst.setString(3, invoice.status);
        pst.setDouble(4, invoice.total);
        pst.setDouble(5, invoice.vat);
    }

    private List<InvoiceDto> resultSetToList(ResultSet rs) throws SQLException {
        List<InvoiceDto> is = new ArrayList<InvoiceDto>();

        while (rs.next())
            is.add(resultSetToInvoice(rs));

        return is;
    }

    private InvoiceDto resultSetToInvoice(ResultSet rs) throws SQLException {
        InvoiceDto i = new InvoiceDto();
        i.id = rs.getLong("id");
        i.number = rs.getLong("number");
        i.date = Util.convertFromSqlDate(rs.getDate("date"));
        i.status = rs.getString("status");
        i.total = rs.getDouble("amount");
        i.vat = rs.getDouble("vat");
        return i;
    }
}
