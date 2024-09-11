package com.example.kms2_08_le_03.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Employee implements Database_Connectable{
    private String firstname;
    private String lastname;
    private String birthdate;
    private String adress;
    private String email;
    private String department;
    private double salary;
    private String hiringdate;

    public Employee(String firstname, String lastname, String birthdate, String adress, String email, String department, double salary, String hiringdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.adress = adress;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.hiringdate = hiringdate;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAdress() {
        return adress;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getHiringdate() {
        return hiringdate;
    }

    @Override
    public String toString() {
        return "Mitarbeiter " +
                "vorname='" + firstname + '\'' +
                ", nachname='" + lastname + '\'' +
                ", geburtsdatum='" + birthdate + '\'' +
                ", adresse='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", abteilung='" + department + '\'' +
                ", gehalt=" + salary +
                ", einstellungsdatum='" + hiringdate + '\'' +
                '}';
    }



    public void saveToDatabase() {
        try (Connection connection = Database_Connection.getConnection()) {
            String sql = "INSERT INTO mitarbeiter (vorname, nachname, geburtsdatum, adresse, email, gehalt, abteilung, einstellungsdatum) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getFirstname());
            statement.setString(2, this.getLastname());
            statement.setString(3, this.getBirthdate());
            statement.setString(4, this.getAdress());
            statement.setString(5, this.getEmail());
            statement.setDouble(6, this.getSalary());
            statement.setString(7, this.getDepartment());
            statement.setString(8, this.getHiringdate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
