package by.academy.home.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3336/";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String NAME = "finaltaskdb";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    Connection conn;

    public Connection getConnection()
            {
                String connectionString = URL + NAME;
                try {
                    Class.forName(DRIVER);
                    conn = DriverManager.getConnection(connectionString, USER, PASSWORD);

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }

                return conn;
            }

}
