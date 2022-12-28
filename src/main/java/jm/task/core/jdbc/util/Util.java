package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД
        private static String Url = "jdbc:mysql://localhost:3306/bd";
        private static String UserName = "root";
        private static String Password = "root";



        public static Connection getConnection() throws SQLException {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(Url, UserName, Password);
//                System.out.println("Подклютение к бд есть!");
            } catch (SQLException e) {
                System.out.println("Подключения к бд нет!");
//                throw new RuntimeException(e);
            }
        return connection;
        }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, Url);
                settings.put(Environment.USER, UserName);
                settings.put(Environment.PASS, Password);
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//                settings.put(Environment.SHOW_SQL, "true");
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Подклютение к бд есть! Hiber");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Нет Hiber!");
            }
        }

        return sessionFactory;
    }

}
