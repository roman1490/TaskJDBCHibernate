package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Иван","Иванов", (byte) 25);
        userService.saveUser("Петр","Петров", (byte) 27);
        userService.saveUser("Федор","Федоров", (byte) 29);
        userService.saveUser("Алексей","Алексеев", (byte) 31);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
