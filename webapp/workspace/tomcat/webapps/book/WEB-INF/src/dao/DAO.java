package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
    static DataSource ds;

    public Connection getConnection() throws Exception {
        if (ds == null) {
            var ic = new InitialContext();
            ds = (DataSource)ic.lookup("java:/comp/env/jdbc/book");
        }
        return ds.getConnection();
    }
}
