package by.academy.home.controller;

import by.academy.home.model.User;
import by.academy.home.service.UserAction;

import java.util.Scanner;

public class UserMenu extends UserAction {
    Scanner sc = new Scanner(System.in);

    public void getUserMenu(User user) {
        boolean done = false;
        while (!done) {
            System.out.println("""
                    1. Список фильмов
                    2. Купленные билеты
                    3. Выйти
                    """);
            switch (sc.nextLine()) {
                case "1":
                    actionWithIvent(user);
                    break;
                case "2":
                    actionWithTicket(user);
                    break;
                case "3":
                    done = true;
                    break;
                default:
                    System.err.println("Введите коректный запрос");
                    break;
            }
        }
    }
}
