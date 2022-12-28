package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
//        Util util = new Util();

//        util.getConnection();
//        util.getSessionFactory();

        UserServiceImpl user1 = new UserServiceImpl();
//        user1.createUsersTable();
//        user1.dropUsersTable();
        user1.saveUser("Иван", "Семёныч", (byte) 23);
        user1.saveUser("Андрей", "Сидорович", (byte) 34);
        user1.saveUser("Вика", "Петрович", (byte) 45);
        user1.saveUser("Да", "Нет", (byte) 103);
//        user1.removeUserById(4);
//        user1.cleanUsersTable();
        System.out.println(user1.getAllUsers());

    }
}
