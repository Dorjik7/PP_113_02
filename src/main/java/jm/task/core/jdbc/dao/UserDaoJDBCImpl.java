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
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users" +
                "(ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "age INT)";
        try (Connection connection = Util.getConnection()) {
           Statement tableCreateStatement = connection.createStatement();
            tableCreateStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            //  assert tableCreateStatement != null;
            //tableCreateStatement.closeOnCompletion();
            System.out.println("Создана новая таблица.");
        }
    }


    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection()) {
            Statement dropTableStatement = connection.createStatement();
            dropTableStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // assert dropTableStatement != null;
            // dropTableStatement.closeOnCompletion();
            System.out.println("Таблица удалена.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO users(name, lastname, age) VALUES(?,?,?)";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            //  assert preparedStatement != null;

            //        preparedStatement.closeOnCompletion();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM users WHERE id";
        try (Connection connection = Util.getConnection()) {
            Statement removeUserStatement = connection.createStatement();
            removeUserStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            //  assert removeUserStatement != null;
            //       removeUserStatement.closeOnCompletion();
            System.out.println("User удален.");
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sqlCommand = "SELECT id, name, lastName, age FROM users";
        try (Connection connection = Util.getConnection()) {
            Statement allUsersStatement = connection.createStatement();
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
            // assert allUsersStatement != null;
            //      allUsersStatement.closeOnCompletion();
            System.out.println("Данные о пользователях: " + allUsers);
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users";
        try (Connection connection = Util.getConnection()) {
            Statement cleanTableStatement = connection.createStatement();
            cleanTableStatement.executeUpdate(sqlCommand);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            //   assert cleanTableStatement != null;
            //      cleanTableStatement.closeOnCompletion();
            System.out.println("Все данные удалены.");
        }
    }
}

