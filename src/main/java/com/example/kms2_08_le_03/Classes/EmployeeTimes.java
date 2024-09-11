package com.example.kms2_08_le_03.Classes;

import com.example.kms2_08_le_03.Classes.Database_Connectable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EmployeeTimes implements Database_Connectable {
    private int employeeID;
    private String startwork;
    private String endwork;
    private String workdate;
    private String startpause;
    private String endpause;

    // Default constructor
    public EmployeeTimes() {
    }



    // konstructor
    public EmployeeTimes(int employeeID, String startwork, String endwork, String workdate, String startpause, String endpause) {
        this.employeeID = employeeID;
        this.startwork = startwork;
        this.endwork = endwork;
        this.workdate = workdate;
        this.startpause = startpause;
        this.endpause = endpause;
    }

    public String getEmployeeID() {
        return Integer.toString(employeeID);
    }

    public String getStartwork() {
        return startwork;
    }

    public String getEndwork() {
        return endwork;
    }

    public String getWorkdate() {
        return workdate;
    }

    public String getStartpause() {
        return startpause;
    }

    public String getEndpause() {
        return endpause;
    }

    @Override
    public String toString() {
        return "Mitarbeiter Zeiten {" +
                "employeeID='" + employeeID + '\'' +
                ", startwork='" + startwork + '\'' +
                ", endwork='" + endwork + '\'' +
                ", workdate='" + workdate + '\'' +
                ", startpause='" + startpause + '\'' +
                ", endpause='" + endpause + '\'' +
                '}';
    }

    public void saveToDatabase() {
        try (Connection connection = Database_Connection.getConnection()) {
            String sql = "INSERT INTO arbeitszeiten (mitarbeiter_id, arbeitsbeginn, arbeitsende, datum, pausebeginn, pauseende) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getEmployeeID());
            statement.setString(2, this.getStartwork());
            statement.setString(3, this.getEndwork());
            statement.setString(4, this.getWorkdate());
            statement.setString(5, this.getStartpause());
            statement.setString(6, this.getEndpause());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] startWork() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        String dateOfStartedWork = now.format(formatterDate);
        String timeOfStartedWork = LocalTime.now().format(formatterTime);
        return new String[]{dateOfStartedWork, timeOfStartedWork};
    }

    public String endWork() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        String timeOfEndedWork = LocalTime.now().format(formatterTime);
        return timeOfEndedWork;
    }

    public String startPause() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        String timeStartPause = LocalTime.now().format(formatterTime);
        return timeStartPause;
    }

    public String endPause() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        String timeEndPause = LocalTime.now().format(formatterTime);
        return timeEndPause;
    }
}
