package com.example.kms2_08_le_03.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;


public class Vacation implements Database_Connectable {
    private int employeeID;
    private String startDate;
    private String endDate;

    public Vacation(int employeeID, String startDate, String vacationName) {
        this.employeeID = employeeID;
        this.startDate = startDate;
        this.endDate = vacationName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Mitarbeiter {" +
                "employeeID=" + employeeID +
                ", Startdate='" + startDate + '\'' +
                ", Enddate='" + endDate + '\'' +
                '}';
    }

    public void saveToDatabase() {
        try (Connection connection = Database_Connection.getConnection()) {
            String sql = "INSERT INTO urlaub (mitarbeiter_id, urlaubsbeginn, urlaubsende) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.getEmployeeID());
            statement.setString(2, this.getStartDate());
            statement.setString(3, this.getEndDate());


            // SQL-Abfrage ausführen
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern der Schulung in die Datenbank: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
