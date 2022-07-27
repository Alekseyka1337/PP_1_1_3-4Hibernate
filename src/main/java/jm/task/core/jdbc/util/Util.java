package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection conn = null;
    private final static String HOST_NAME = "localhost";
    private final static String BD_NAME = "Mysql";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "1337";

    Util() {
        try {
            if (null == conn || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://" + HOST_NAME + ":3306/" + BD_NAME, USER_NAME, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось подключится к базе данных");
        }
    }

    public static Connection getMySQLConnection() {
        new Util();
        return conn;
    }
}