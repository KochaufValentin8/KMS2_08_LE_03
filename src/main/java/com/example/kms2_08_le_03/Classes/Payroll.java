package com.example.kms2_08_le_03.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Payroll implements Database_Connectable{
    private int employeeID;
    private String date;
    private double salaryBrutto;
    private double taxesValue;
    private double salaryNetto;
    private int taxrate;


    public Payroll(int employeeID, String date, double salaryBrutto, double taxesValue, double salaryNetto, int taxrate) {
        this.employeeID = employeeID;
        this.date = date;
        this.salaryBrutto = salaryBrutto;
        this.taxesValue = taxesValue;
        this.salaryNetto = salaryNetto;
        this.taxrate = taxrate;

    }


    public int getEmployeeID() {
        return employeeID;
    }

    public String getDate() {
        return date;
    }

    public double getSalaryBrutto() {
        return salaryBrutto;
    }

    public double getTaxesValue() {
        return taxesValue;
    }

    public double getSalaryNetto() {
        return salaryNetto;
    }

    public int getTaxrate() {
        return taxrate;
    }


    @Override
    public String toString() {
        return "Mitarbeiter " +
                "employeeID='" + employeeID + '\'' +
                ", date='" + date + '\'' +
                ", salaryBrutto='" + salaryBrutto + '\'' +
                ", taxesValue='" + taxesValue + '\'' +
                ", salaryNetto='" + salaryNetto + '\'' +
                ", taxrate='" + taxrate + '\'' +
                '}';
    }



    public void saveToDatabase() {
        try (Connection connection = Database_Connection.getConnection()) {
            String sql = "INSERT INTO gehaltsabrechnung (mitarbeiter_id, abrechnungsdatum, brutto_gehalt, steuer_abzug, netto_gehalt, steuersatz) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.getEmployeeID());
            statement.setString(2, this.getDate());
            statement.setDouble(3, this.getSalaryBrutto());
            statement.setDouble(4, this.getTaxesValue());
            statement.setDouble(5, this.getSalaryNetto());
            statement.setInt(6, this.getTaxrate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static double[] CalculatePayroll(double salaryBrutto){

        int taxRate = getTaxRate(salaryBrutto);
        double tax = (salaryBrutto * taxRate) / 100;  // steuern = gehalt * (steuersatz in % durch 100 für dezimalzahl)

        double salaryNetto = salaryBrutto - tax;
        System.out.println("Gehalt Brutto: " + salaryBrutto + ", Gehalt Netto: " + salaryNetto + ", Steuern: " + tax + ", Steuersatz: " + taxRate + "%");
        double[] payrollInfo = {tax, salaryNetto};
        return payrollInfo;

    }

    public static int getTaxRate(double einkommen) {
        // TreeMap für die Einkommensgrenzen und Steuersätze
        NavigableMap<Double, Integer> taxrates = new TreeMap<>();

        // lphnsteuertabelle quelle: https://finanzrechner.at/brutto-netto/lohnsteuertabelle
        taxrates.put(1079.00, 0);
        taxrates.put(1745.83, 20);
        taxrates.put(2887.08, 30);
        taxrates.put(5562.00, 40);
        taxrates.put(8283.17, 48);
        taxrates.put(83344.33, 50);
        taxrates.put(Double.MAX_VALUE, 55); // Für Einkommen über 83.344,33 €

        // Den größten Schlüssel kleiner oder gleich dem Einkommen finden der part ist von Chat GPT
        Map.Entry<Double, Integer> entry = taxrates.floorEntry(einkommen);
        return entry.getValue();
    }


    public static String GetDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateOfStartedWork = now.format(formatterDate);
        return dateOfStartedWork;
    }

    static public void MakePayRoll(int id, Double salaryBrutto){
        double[] taxAndSalaryNetto = CalculatePayroll(salaryBrutto);
        String date = GetDate();
        int taxrate = getTaxRate(salaryBrutto);

        Payroll newPayroll = new Payroll(id, date, salaryBrutto, taxAndSalaryNetto[0], taxAndSalaryNetto[1], taxrate);
        newPayroll.saveToDatabase();
    }//(mitarbeiter_id, abrechnungsdatum, brutto_gehalt, steuer_abzug, netto_gehalt, steuersatz)
}

