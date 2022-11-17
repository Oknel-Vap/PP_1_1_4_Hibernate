package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    String sql_request;

    public void createUsersTable() {
        try (Statement stmt = Util.getConnection().createStatement()) {
            sql_request = "CREATE TABLE IF NOT EXISTS Users " +
                    "(ID SERIAL PRIMARY KEY, " +
                    " NAME_USER VARCHAR(255) NOT NULL, " +
                    " LASTNAME VARCHAR(255) NOT NULL, " +
                    " AGE INTEGER)";
            stmt.executeUpdate(sql_request);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = Util.getConnection().createStatement()) {
            sql_request = "DROP TABLE IF EXISTS Users";
            stmt.execute(sql_request);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        sql_request = "INSERT INTO Users (NAME_USER, LASTNAME, AGE) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = Util.getConnection().prepareStatement(sql_request)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement stmt = Util.getConnection().createStatement()) {
            sql_request = "DELETE from Users where ID=id";
            stmt.execute(sql_request);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement stmt = Util.getConnection().createStatement()) {
            sql_request = "SELECT * from Users";
            ResultSet rs = stmt.executeQuery(sql_request);
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name_user");
                String lastName = rs.getString("lastName");
                Byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(rs.getLong("ID"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement stmt = Util.getConnection().createStatement()) {
            sql_request = "TRUNCATE Users";
            stmt.execute(sql_request);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
