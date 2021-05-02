package by.academy.home.service;

import by.academy.home.model.User;

import java.util.ArrayList;
import java.util.List;

public class SuperAdminAction extends AdminAction {
    List<User> userList = new ArrayList();

    public void actionWithUsr(String username) {

        dbUser.getUserFromDb(userList, username);
        System.out.println(userList);
        System.out.println("""
        Дать пользователю права админа?
        1.да
        2.нет
        """);
        switch (sc.nextLine()) {
            case "1":
                makeFromThisUsrManager(username);
                break;
            case "2":
                System.out.println("Ладно");
                break;
            default:
                System.out.println("Ошибиться между 1 и 2.... Хорош");
                break;
        }
    }

    private void makeFromThisUsrManager(String username) {
        dbUser.fromZeroToHero(checkUserAvailability(username));
    }
}
