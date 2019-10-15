package uo.ri.common;

import java.sql.Date;

public class Util {

    public static Date convertToSqlDate(java.util.Date date) {
        return new Date(date.getTime());
    }

    public static java.util.Date convertFromSqlDate(Date date) {
        return new java.util.Date(date.getTime());
    }

}
