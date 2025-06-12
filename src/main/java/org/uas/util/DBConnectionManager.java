package org.uas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionManager {
    private static final String DB_URL = "jdbc:sqlite:dbuas.db";
    private static Connection connection;

    private DBConnectionManager() {

    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return connection;
    }
}

