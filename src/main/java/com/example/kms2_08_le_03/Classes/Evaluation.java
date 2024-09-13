package com.example.kms2_08_le_03.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Evaluation implements Database_Connectable {
    private int employeeID;
    private String date;
    private int rating;

    public Evaluation(int employeeID, String date, int rating) {
        this.employeeID = employeeID;
        this.date = date;
        this.rating = rating;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Mitarbeiter {" +
                "employeeID=" + employeeID +
                ", date='" + date + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

    public void saveToDatabase() {
        try (Connection connection = Database_Connection.getConnection()) {
            String sql = "INSERT INTO leistungsbewertungen (mitarbeiter_id, bewertungsdatum, bewertungsnote) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.getEmployeeID());
            statement.setString(2, this.getDate());
            statement.setInt(3, this.getRating());


            // SQL abfrage ausführen
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern der Schulung in die Datenbank: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void AddEvaluation(int id, int ratingEvaluation) {
        String date = DateOfToday();
        Evaluation newEvaluation = new Evaluation(id, date, ratingEvaluation);
        newEvaluation.saveToDatabase();
    }
    private static String DateOfToday() {
        LocalDate heute = LocalDate.now();  // Heutiges Datum
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Formatter
        return heute.format(formatter);  // formatiertes datum zurückgeben
    }
}
