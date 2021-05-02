package by.academy.home.connection;

import by.academy.home.model.Ticket;
import by.academy.home.model.User;

import java.sql.*;
import java.util.List;

public class DatabaseUser extends DatabaseConnection {

    private String access = "";
    HashFunction hash = new HashFunction();

    public void signUpUser(String name, String password) {


        if (name.equals("") && password.equals("")) {
            System.out.println("Введите коректные данные");
        } else {


            String insert = "INSERT INTO " + Const.USER_TABLE +
                    "(" + Const.USERS_NAME + "," + Const.USERS_PASSWORD +
                    "," + Const.USERS_ACCESS + ")" + " VALUES(?, ? , ?);";

            try (PreparedStatement prSt = getConnection().prepareStatement(insert)) {
                prSt.setString(1, name);
                prSt.setString(2, hash.hashMaker(password));
                prSt.setString(3, "user");
                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public String getAccess() {
        return access;
    }

    public User authorization(String name, String password) {
        String insert = "SELECT * FROM `users` WHERE name = '" + name + "' AND password = '" + hash.hashMaker(password) + "'";
        boolean result;
        User user = null;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(insert);
            result = rs.next();

            if (!result) {
                System.out.println("Аккаунт не найден");
            } else {
                System.out.println("Вход выполнен");
                user =
                        new User(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("password"),
                                rs.getString("access"));
                this.access = rs.getString("access");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public int findUser(String username) {
        String insert = "SELECT `id` FROM `users` WHERE name = \"" + username + "\";";
        int id = 0;
        boolean result;
        User user = null;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(insert);
            result = rs.next();

            if (!result) {
                System.err.println("Не найден");
            } else {
                System.out.println("Найден!");
                id = rs.getInt("id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public void getUserFromDb(List<User> list, String username) {
        String insert = "SELECT * FROM `users` WHERE name = \"" + username + "\";";
        System.out.println(insert);
        int id = 0;
        boolean result;
        User user = null;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(insert);
            result = rs.next();

            if (!result) {
                System.err.println("Не найден");
            } else {
                System.out.println("Найден!");
                list.clear();
                list.add(new User(rs.getInt("id"),
                        rs.getString("name"),
                        "null", rs.getString("access")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void fromZeroToHero(int id) {
        String insert = "UPDATE `users` SET `access`= \"admin\" WHERE id = \"" + id + "\";";

        try (PreparedStatement prSt = getConnection().prepareStatement(insert)) {

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
