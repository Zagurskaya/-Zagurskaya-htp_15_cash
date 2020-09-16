package com.gmail.zagurskaya.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnCreator {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }
    }

    private static volatile Connection connection;

    private ConnCreator() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (ConnCreator.class) {
                if (connection == null || connection.isClosed()) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection(
                                CN.URL, CN.USER, CN.PASSWORD
                        );
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return connection;
    }

    public static Connection getConnectionChema() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (ConnCreator.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(
                            CN.URLCHEMA, CN.USER, CN.PASSWORD
                    );
                }
            }

        }
        return connection;
    }
}
