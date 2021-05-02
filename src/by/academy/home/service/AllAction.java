package by.academy.home.service;

import by.academy.home.connection.Const;
import by.academy.home.connection.DatabaseFilm;
import by.academy.home.connection.DatabaseTicket;
import by.academy.home.connection.DatabaseUser;
import by.academy.home.model.Film;
import by.academy.home.model.Ticket;
import by.academy.home.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class AllAction {


    List<Film> filmList = new ArrayList<>();
    List<Ticket> ticketList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    DatabaseFilm dbFilm = new DatabaseFilm();
    DatabaseTicket dbTicket = new DatabaseTicket();
    DatabaseUser dbUser = new DatabaseUser();


    public void addFilm(String name, String description, String cost, LocalDate date) {

        dbFilm.createFilm(name, description, cost, date);
        addTicket(filmList.get(filmList.size() - 1).getIdFilm());
    }

    public static LocalDate createDate(String date) {
        String[] str = date.split("-");
        return LocalDate.of(Integer.parseInt((str[0])), Integer.parseInt((str[1])), Integer.parseInt((str[2])));

    }

    private static void addTicket(int filmId) {
        DatabaseTicket dbTicked = new DatabaseTicket();
        for (int i = 1; i <= Const.CINEMA_SEATS; i++) {
            dbTicked.createTickets(filmId, i);
        }
    }

    public void showAllFilms(User user) {
        try {
            System.out.println(Arrays.toString(dbFilm.getListNameFilms()));
            System.out.println("Выбрать фильм");
            String buf = sc.nextLine();
            dbFilm.getDataFilm(filmList, buf);
            System.out.println(editedMovieDescription());
            System.out.println("Билеты в наличии:\n");
            dbTicket.getDataTicket(ticketList, String.valueOf(filmList.get(0).getIdFilm()));
            System.out.println(ticketList);
            dbTicket.buyTicket(user.getId(), sc.nextInt());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Введите корректные значения");
        }
    }

    public void getUserTicket(User user) {
        try {

            dbTicket.getPurchasedTicket(user.getId(), ticketList);
            dbFilm.getDataFilm(filmList, ticketList.get(0).getFilmIdTicket());
            for (Film film : filmList) {
                for (Ticket ticket : ticketList) {
                    if (film.getIdFilm() == ticket.getFilmIdTicket()) {
                        System.out.println(film.getNameFilm() + " - " + ticket.getNumber());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Что-то пошло не так");
        }
    }


    private String editedMovieDescription() {
        StringBuilder sb = new StringBuilder();
        int n = filmList.size() - 1;
        sb.append(filmList.get(n).getNameFilm()).append("\n")
                .append(splitter(filmList.get(n).getDescription())).append("\n")
                .append("Цена билета - ").append(filmList.get(n).getCostFilm());
        return sb.toString();

    }

    private String splitter(String input) {
        Pattern pattern = Pattern.compile("\\s");
        String[] words = pattern.split(input);
        StringBuilder resault = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (!((i + 1) % 6 == 0)) {
                resault.append(words[i]).append(" ");
            } else {
                resault.append(words[i]).append("\n");
            }
        }
        return resault.toString();
    }

    public String editedTicketList(List<Ticket> list) {

        StringBuilder sb = new StringBuilder();
        for (Ticket ticket : list) {
            dbFilm.getDataFilm(filmList, ticket.getFilmIdTicket());
            sb.append("\n").append(filmList.get(0).getNameFilm())
                    .append(" - ").append(ticket).append("\n");
        }
        return sb.toString();
    }

    public void checkUserTicket(String username) {

        dbTicket.getDataUserTicket(ticketList, checkUserAvailability(username));

    }

    public int checkUserAvailability(String username) {
        return dbUser.findUser(username);
    }

    public void removeTicket (int filmId, int ticketNumb){
        dbTicket.removeTicket(filmId, ticketNumb);
    }
    public int   getIdFilm (String filmName){
        dbFilm.getDataFilm(filmList, filmName);
        return filmList.get(0).getIdFilm();
    }
}
