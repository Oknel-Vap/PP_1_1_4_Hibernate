package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao table = new UserDaoJDBCImpl();
        table.createUsersTable();
        User user = new User("John", "Snow", (byte) 15);
        for (int i = 0; i < 4; i++) {
            table.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
        }
        List<User> userList = table.getAllUsers();
        userList.forEach(System.out::println);
        table.cleanUsersTable();
        table.dropUsersTable();
    }
}
