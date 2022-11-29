package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration()
                        .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/pp_base")
                        .setProperty("hibernate.connection.username", "postgres")
                        .setProperty("hibernate.connection.password", "1234")
                        .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
                        .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                        .addAnnotatedClass(User.class);
                sessionFactory = cfg.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


    private static final String db_url = "jdbc:postgresql://localhost:5432/pp_base";
    private static final String user = "postgres";
    private static final String pass = "1234";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(db_url, user, pass);
        return connection;
    }
}
