package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Павел", "Дуров", (byte) 37);
        userService.saveUser("Герман", "Греф", (byte) 58);
        userService.saveUser("Олег", "Тиньков", (byte) 54);
        userService.saveUser("Аркадий", "Волож", (byte) 58);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
