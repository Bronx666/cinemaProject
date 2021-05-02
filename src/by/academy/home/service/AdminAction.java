package by.academy.home.service;

import java.time.LocalDate;

public class AdminAction extends UserAction {

    public void createMovie() {
        System.out.println("Название фильма:");
        String name = sc.nextLine();
        System.out.println("Описание фильма:");
        String description = sc.nextLine();
        System.out.println("Цена билета:");
        String cost = sc.nextLine();
        System.out.println("Дата показа (ГГГГ-ММ-ДД)");
        LocalDate date = createDate(sc.nextLine());

        addFilm(name, description, cost, date);
    }

    public void actionWithTicketUsers() {
        System.out.println("Билеты какого пользователя будем редактировать?");
        checkUserTicket(sc.nextLine());
        System.out.println(editedTicketList(ticketList));
        System.out.println("""
                1.Удалить
                2.Добавить
                3.Назад
                """);
        boolean done = false;
        while (!done) {
            switch (sc.nextLine()) {
                case "1":
                    System.out.println("Введите название фильма");
                    int film = getIdFilm(sc.nextLine());
                    System.out.println("Введите номер билета");
                    int ticketNumb = sc.nextInt();
                    removeTicket(film,ticketNumb);
                    break;
                case "2":
                    break;
                case "3":
                    done = true;
                    break;
                default:
                    System.err.println("Введите корректное значение");
            }
        }
    }
}
