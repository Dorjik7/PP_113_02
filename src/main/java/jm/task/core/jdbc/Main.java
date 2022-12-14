package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Adam", "First", (byte) 101);
    private static final User user2 = new User("Eve", "Second", (byte) 100);
    private static final User user3 = new User("Lilith", "Zero", (byte) 125);
    private static final User user4 = new User("Snake", "Apple", (byte) 125);

    public static void main(String[] args) {

        userService.createUsersTable();
        if (userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge())) {
            System.out.println("User с именем – " + "name" + "добавлен в базу данных");
        }
        if (userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge())) {
            System.out.println("User с именем – " + "name" + "добавлен в базу данных");
        }

        if (userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge())) {
            System.out.println("User с именем – " + "name" + "добавлен в базу данных");
        }

        if (userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge())) {
            System.out.println("User с именем – " + "name" + "добавлен в базу данных");
        }


        System.out.println(userService.getAllUsers());


        // userService.removeUserById(2);

        // userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
