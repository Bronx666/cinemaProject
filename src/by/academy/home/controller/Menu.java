package by.academy.home.controller;

import by.academy.home.connection.DatabaseUser;
import by.academy.home.model.User;

import java.util.Scanner;

public class Menu {

    public void start() {

        DatabaseUser dbUser = new DatabaseUser();
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {

            System.out.println("""
            У вас уже есть аккаунт?
            da/net
            /exit для выхода
            """);

            switch (sc.nextLine()) {

                case "da" -> {
                    System.out.println("Введите логин и пароль!");
                    User user = dbUser.authorization(sc.nextLine(), sc.nextLine());
                    switch (dbUser.getAccess()) {

                        case "user" -> {
                            UserMenu userStart = new UserMenu();
                            userStart.getUserMenu(user);
                            done = true;
                        }

                        case "admin" -> {
                            AdminMenu adminStart = new AdminMenu();
                            adminStart.getAdminMenu(user);
                            done = true;
                        }

                        case "superAdmin" -> {
                            SuperAdminMenu saMenu = new SuperAdminMenu();
                            saMenu.getSuperAdminMenu(user);
                            done = true;
                        }
                    }
                }
                case "net" -> {
                    System.out.println("Придумайте логин и пароль");
                    dbUser.signUpUser(sc.nextLine(), sc.nextLine());

                }
                case "/exit" -> done = true;
                default -> System.err.println("Введите коректный запрос");
            }
        }
        System.out.println("Спасибо за внимание!");
    }
}

