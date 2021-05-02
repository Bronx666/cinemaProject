package by.academy.home.connection;

import by.academy.home.model.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseTicket extends DatabaseConnection {
    Statement stmt = null;

    public void createTickets(int filmId, int number) {

        String insert = "INSERT INTO " + Const.TICKETS_TABLE +
                "( `filmTicket`, `numberTicket`, `stateTicket`) VALUES (?,?,?);";

        try (PreparedStatement prSt = getConnection().prepareStatement(insert)) {
            prSt.setString(1, String.valueOf(filmId));
            prSt.setString(2, String.valueOf(number));
            prSt.setString(3, "0");
            prSt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getDataTicket(List<Ticket> list, String filmId) {
        String insert = "SELECT * FROM `tickets` WHERE filmTicket = \"" + filmId + "\" AND stateTicket = 0;";

        try {

            list.clear();
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            int columns = rs.getMetaData().getColumnCount();
            String[] str = new String[columns];
            int index = 0;
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    str[i - 1] = rs.getString(i);
                }
                list.add(index, new Ticket(Integer.parseInt(str[0]), checkNull(str[1]),
                        Integer.parseInt(str[2]), Integer.parseInt(str[3]), getBoolean(str[4])));
                index++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean getBoolean(String a) {
        return switch (a) {
            case "0" -> false;
            case "1" -> true;

            default -> throw new IllegalStateException("Unexpected value: " + a);
        };
    }

    private int checkNull(String id) {

        if (id == null) {
            return 0;
        } else {
            return Integer.parseInt(id);
        }
    }

    public void buyTicket(int userId, int idTicket) {
        String insert = "UPDATE `tickets` SET `userTicket`= ?, stateTicket = 1 WHERE `numberTicket` = ? ;";

        try (PreparedStatement prSt = getConnection().prepareStatement(insert)) {
            prSt.setString(1, String.valueOf(userId));
            prSt.setString(2, String.valueOf(idTicket));
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getPurchasedTicket(int userId, List<Ticket> list) {
        String insert = "SELECT * FROM `tickets` WHERE userTicket = \"" + userId + "\";";
        try {

            list.clear();
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            int columns = rs.getMetaData().getColumnCount();
            String[] str = new String[columns];
            int index = 0;
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    str[i - 1] = rs.getString(i);
                }
                list.add(index, new Ticket(Integer.parseInt(str[0]), checkNull(str[1]),
                        Integer.parseInt(str[2]), Integer.parseInt(str[3]), getBoolean(str[4])));
                index++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getDataUserTicket(List<Ticket> list, int id) {
        String insert = "SELECT * FROM `tickets` WHERE userTicket = \"" + id + "\" ;";
        try {

            list.clear();
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            int columns = rs.getMetaData().getColumnCount();
            String[] str = new String[columns];
            int index = 0;
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    str[i - 1] = rs.getString(i);
                }
                list.add(index, new Ticket(Integer.parseInt(str[0]), checkNull(str[1]),
                        Integer.parseInt(str[2]), Integer.parseInt(str[3]), getBoolean(str[4])));
                index++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeTicket(int filmId, int ticketNumb) {
        String insert = "UPDATE `tickets` SET `stateTicket`= \"0\", userTicket = null WHERE filmTicket = \""
                + filmId + "\" AND numberTicket = \"" + ticketNumb + "\";";
        System.out.println(insert);
        try (PreparedStatement prSt = getConnection().prepareStatement(insert)) {
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Ошибка");
        }


    }
}
