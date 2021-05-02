package by.academy.home;

import by.academy.home.connection.HashFunction;
import by.academy.home.controller.Menu;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Menu menu = new Menu();
        menu.start();
    }


}