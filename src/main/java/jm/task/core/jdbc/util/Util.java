package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Util {
    private static final String db_url = "jdbc:postgresql://localhost:5432/pp_base";
    private static final String user = "postgres";
    private static final String pass = "1234";
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(db_url,user,pass);
        return connection;
    }

}
