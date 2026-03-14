package com.hospital.management.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Just_tm1i";

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }

        } catch (SQLException e) {

            System.out.println("Connection Failed.");
        }

        return connection;
    }

}