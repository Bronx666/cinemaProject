package by.academy.home.connection;


import by.academy.home.model.Film;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static by.academy.home.service.AllAction.createDate;

public class DatabaseFilm extends DatabaseConnection {
    Statement stmt = null;

    public void createFilm(String name, String description, String cost, LocalDate date) {

        String insert = "INSERT INTO " + Const.FILM_TABLE +
                "(`nameFilm`, `descriptionFilm`, `costFilm`, `dateFilm`) VALUES (?,?,?,?);";
        //System.out.println("Введите: название фильма, описание, цену, дату");
        try (PreparedStatement prSt = getConnection().prepareStatement(insert)) {
            prSt.setString(1, name);
            prSt.setString(2, description);
            prSt.setString(3, cost);
            prSt.setString(4, String.valueOf(date));
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getDataFilm(List<Film> list,int id) {

        try {
            list.clear();
            String insert = "SELECT * FROM `films` WHERE idFilm = \"" + id + "\" ;";
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            int columns = rs.getMetaData().getColumnCount();
            String[] str = new String[columns];
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    str[i - 1] = rs.getString(i);
                }
                list.add( new Film(Integer.parseInt(str[0]), str[1], str[2],
                        Integer.parseInt(str[3]), createDate(str[4])));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Введено неверное название");
        }
    }
    public void getDataFilm(List<Film> list, String name) {

        try {
            list.clear();
            String insert = "SELECT * FROM `films` WHERE nameFilm = \"" + name + "\" ;";
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            int columns = rs.getMetaData().getColumnCount();
            String[] str = new String[columns];
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    str[i - 1] = rs.getString(i);
                }
                list.add( new Film(Integer.parseInt(str[0]), str[1], str[2],
                        Integer.parseInt(str[3]), createDate(str[4])));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Введено неверное название");
        }
    }

    public String[] getListNameFilms() {
        String insert = "SELECT `nameFilm` FROM `films` WHERE 1;";
        String[] str = new String[0];
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            int columns = rs.getMetaData().getColumnCount();
            str = new String[columns];
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    str[i - 1] = rs.getString(i);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str;
    }
}
