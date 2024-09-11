package com.example.kms2_08_le_03.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Training implements Database_Connectable {
    private int employeeID;
    private String date;
    private String trainingName;

    public Training(int employeeID, String date, String trainingName) {
        this.employeeID = employeeID;
        this.date = date;
        this.trainingName = trainingName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getDate() {
        return date;
    }

    public String getTrainingName() {
        return trainingName;
    }

    @Override
    public String toString() {
        return "Mitarbeiter {" +
                "employeeID=" + employeeID +
                ", date='" + date + '\'' +
                ", trainingName='" + trainingName + '\'' +
                '}';
    }

    public void saveToDatabase() {
        try (Connection connection = Database_Connection.getConnection()) {
            String sql = "INSERT INTO schulungen (mitarbeiter_id, schulungsname, datum) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.getEmployeeID());
            statement.setString(2, this.getTrainingName());
            statement.setString(3, this.getDate());

            // SQL-Abfrage ausführen
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern der Schulung in die Datenbank: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void AddTraining(int id, String name, String date) {
        Training newTraining = new Training(id, date, name);
        newTraining.saveToDatabase();
    }
}
