package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    String sql_request;

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql_request = "CREATE TABLE IF NOT EXISTS Users " +
                    "(ID SERIAL PRIMARY KEY, " +
                    " NAME_USER VARCHAR(255) NOT NULL, " +
                    " LASTNAME VARCHAR(255) NOT NULL, " +
                    " AGE INTEGER)";

            Query query = session.createNativeQuery(sql_request);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql_request = "DROP TABLE IF EXISTS Users";
            Query query = session.createNativeQuery(sql_request);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении таблицы");
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении пользователя");
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql_request = "DELETE from Users where ID=id";
            Query query = session.createNativeQuery(sql_request);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя по номеру id");
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql_request = "SELECT * from Users";
            Query<User> query = session.createNativeQuery(sql_request).addEntity(User.class);
            List<User> userList = query.getResultList();
            return userList;
        } catch (Exception e) {
            System.out.println("Ошибка при получении списка пользователей");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql_request = "TRUNCATE Users";
            Query query = session.createNativeQuery(sql_request);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка при очищении таблицы");
            e.printStackTrace();
        }
    }
}
