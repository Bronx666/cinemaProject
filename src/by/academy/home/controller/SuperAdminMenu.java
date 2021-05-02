package by.academy.home.controller;

import by.academy.home.model.User;
import by.academy.home.service.SuperAdminAction;

import java.util.Scanner;

public class SuperAdminMenu extends SuperAdminAction {

    public void getSuperAdminMenu(User user) {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.println("""
                    1. Список фильмов
                    2. Купленные билеты
                    3. Добавить фильм
                    4. Билеты N пользователя
                    5. Редактор менеждеров
                                                            
                    7. Выйти
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
                    boolean case5 = false;
                    while (!case5) {
                        System.out.println("""
                                1. Работа с польхователями
                                2. назад
                                """);
                        switch (sc.nextLine()) {
                            case "1":
                                System.out.println("Выбрать пользователя");
                                actionWithUsr(sc.nextLine());
                                break;
                            case "2":
                                case5 = true;
                                break;
                            default:
                                System.out.println("Введите коректные значения!");
                                break;
                        }
                    }
                    break;
                case "7":
                    done = true;
                    break;
                default:
                    System.err.println("Введите коректный запрос");
                    break;
            }

        }

    }
}
