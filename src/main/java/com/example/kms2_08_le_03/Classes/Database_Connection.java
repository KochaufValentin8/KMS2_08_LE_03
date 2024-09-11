package com.example.kms2_08_le_03.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database_Connection {

    private static final String URL = "jdbc:mysql://localhost:3306/dp_pv";
    private static final String BENUTZERNAME = "root";
    private static final String PASSWORT = "Vali123!";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, BENUTZERNAME, PASSWORT);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler! Verbindung zur Datenbank fehlgeschlagen");
        }
    }


    public static void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Verbindung zur Datenbank fehlgeschlagen.");
        }
    }
}
