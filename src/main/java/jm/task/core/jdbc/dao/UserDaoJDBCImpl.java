package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Statement tableCreateStatement = null;
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users" +
                "(ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "age INT)";
        try {
            tableCreateStatement = Util.getConnection().createStatement();
            tableCreateStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            assert tableCreateStatement != null;
            try {
                tableCreateStatement.closeOnCompletion();
                System.out.println("Создана новая таблица.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void dropUsersTable() {
        Statement dropTableStatement = null;
        String sqlCommand = "DROP TABLE IF EXISTS users";
        try {
            dropTableStatement = Util.getConnection().createStatement();
            dropTableStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            assert dropTableStatement != null;
            try {
                dropTableStatement.closeOnCompletion();
                System.out.println("Таблица удалена.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sqlCommand = "INSERT INTO users(name, lastname, age) VALUES(?,?,?)";
        try {
            preparedStatement = Util.getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            try {
                preparedStatement.closeOnCompletion();
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Statement removeUserStatement = null;
        String sqlCommand = "DELETE FROM users WHERE id";
        try {
            removeUserStatement = Util.getConnection().createStatement();
            removeUserStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            assert removeUserStatement != null;
            try {
                removeUserStatement.closeOnCompletion();
                System.out.println("User удален.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Statement allUsersStatement = null;
        List<User> allUsers = new ArrayList<>();
        String sqlCommand = "SELECT id, name, lastName, age FROM users";
        try {
            allUsersStatement = Util.getConnection().createStatement();
            ResultSet results = allUsersStatement.executeQuery(sqlCommand);
            while (results.next()) {
                User user = new User();
                user.setId(results.getLong("id"));
                user.setName(results.getString("name"));
                user.setLastName(results.getString("lastName"));
                user.setAge(results.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            assert allUsersStatement != null;
            try {
                allUsersStatement.closeOnCompletion();
                System.out.println("Данные о пользователях: " + allUsers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        Statement cleanTableStatement = null;
        String sqlCommand = "DELETE FROM users";
        try {
            cleanTableStatement = Util.getConnection().createStatement();
            cleanTableStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            assert cleanTableStatement != null;
            try {
                cleanTableStatement.closeOnCompletion();
                System.out.println("Все данные удалены.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

