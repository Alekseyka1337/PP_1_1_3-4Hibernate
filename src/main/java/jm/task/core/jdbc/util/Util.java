package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection conn = null;
    private static SessionFactory sessionFactory = null;
    private final static String HOST_NAME = "localhost";
    private final static String BD_NAME = "Mysql";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "1337";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    Util() {
        try {
            if (null == conn || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://" + HOST_NAME + ":3306/" + BD_NAME, USER_NAME, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось подключится к базе данных");
        }
    }

    Util(Configuration configuration, Properties settings) {
        try {
            settings.put(Environment.DRIVER, DRIVER);
            settings.put(Environment.URL, "jdbc:mysql://" + HOST_NAME + ":3306/" + BD_NAME + "?autoReconnect=true&useSSL=false");
            settings.put(Environment.USER, USER_NAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (HibernateException e) {
            System.out.println("Problem creating session factory");
            e.printStackTrace();
        }
    }

    public static Connection getMySQLConnection() {
        new Util();
        return conn;
    }

    public static SessionFactory getSessionFactory() {
        new Util(new Configuration(), new Properties());
        return sessionFactory;

    }
}