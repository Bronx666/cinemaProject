package by.academy.home.controller;

import by.academy.home.model.User;
import by.academy.home.service.AdminAction;

import java.util.Scanner;

public class AdminMenu extends AdminAction {

    Scanner sc = new Scanner(System.in);

    public void getAdminMenu(User user) {
        boolean done = false;
        while (!done) {
            System.out.println("""
                    1. Список фильмов
                    2. Купленные билеты
                    3. Добавить фильм
                    4. Билеты N пользователя
                                                            
                    5. Выйти
                    """);
            switch (sc.nextLine()) {
                case "1":
                    actionWithIvent(user);
                    break;
                case "2":
                    actionWithTicket(user);
                    break;
                case "3":
                    createMovie();
                    break;
                case "4":
                    actionWithTicketUsers();
                    break;
                case "5":
                    done = true;
                    break;
                default:
                    System.err.println("Введите коректный запрос");
                    break;
            }
        }
    }
}

