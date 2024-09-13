package com.example.kms2_08_le_03.Classes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Data_Processing {



    public static String CurrentDate() {

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = now.format(formatter);
        return today;

    }


    static void CalculatePayroll(double salary){
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

        int taxRate = getTaxRate(salary, taxrates);
        double tax = salary * (taxRate / 100); //steuern = gehalt * (steuersatz in % durch 100 für dezimalzahl)

        double salaryNetto = salary - tax;
        System.out.println("Gehalt Brutto: " + salary + ", Gehalt Netto: " + salaryNetto + ", Steuern: " + tax + ", Steuersatz: " + taxRate + "%");


    }
    public static int getTaxRate(double einkommen, NavigableMap<Double, Integer> taxrates) { //chatgpt super lösung
        // Den größten Schlüssel kleiner oder gleich dem Einkommen finden
        Map.Entry<Double, Integer> entry = taxrates.floorEntry(einkommen); // ceilingEntry(); ist für den kleinsten finden
        return entry.getValue();


}
}
