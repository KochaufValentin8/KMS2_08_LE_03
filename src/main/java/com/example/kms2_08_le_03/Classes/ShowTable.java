package com.example.kms2_08_le_03.Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ShowTable {

    public static List<String> AllEmployees(){

        String sqlString = "SELECT * FROM mitarbeiter";

            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                //Datenbank verbindung
                connection = Database_Connection.getConnection();

                // sql abfrage
                statement = connection.createStatement();


                    resultSet = statement.executeQuery(sqlString);

                List<String> allEmployeesInfo = new ArrayList<>();
                    // Ergebnisse durchlaufen und anzeigen
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String firstName = resultSet.getString("vorname");
                        String lastName = resultSet.getString("nachname");
                        allEmployeesInfo.add(id + ", " + firstName + ", " + lastName);
                    }
                    return allEmployeesInfo;


            } catch (SQLException e) {
                e.printStackTrace();
            }


        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;

    }



    public static Map<Integer, Double> GetIDandSalary() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Datenbankverbindung
            connection = Database_Connection.getConnection();

            // SQL-Abfrage
            statement = connection.createStatement();

            // Abfrage und Ergebnis vom SQL-String
            String sqlString = "SELECT id, gehalt FROM mitarbeiter";
            Map<Integer, Double> idsWithSalary = new HashMap<>();

            resultSet = statement.executeQuery(sqlString);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double salary = resultSet.getDouble("gehalt");
                idsWithSalary.put(id, salary);
            }

            return idsWithSalary;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public static List<Integer> GetIds(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        List<Integer> idList = new ArrayList<>();
try{
        //Datenbank verbindung
        connection = Database_Connection.getConnection();

        // sql abfrage
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT id FROM mitarbeiter");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            idList.add(id);

        }
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    }

