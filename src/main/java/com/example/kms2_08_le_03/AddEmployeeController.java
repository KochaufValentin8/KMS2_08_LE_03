package com.example.kms2_08_le_03;

import com.example.kms2_08_le_03.Classes.Data_Processing;
import com.example.kms2_08_le_03.Classes.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddEmployeeController {

    @FXML
    private TextField employeeFirstNameField;

    @FXML
    private TextField employeeLastNameField;

    @FXML
    private DatePicker employeeBirthDateField;

    @FXML
    private TextField employeeAddressField;

    @FXML
    private TextField employeeEmailField;

    @FXML
    private TextField employeeSalaryField;

    @FXML
    private TextField employeeDepartmentField;

    @FXML
    private Label statusLabel;





    @FXML
    private Button addButton;

    @FXML
    private void handleAddEmployee() {
        String firstName = employeeFirstNameField.getText();
        String lastName = employeeLastNameField.getText();
        LocalDate birthDate = employeeBirthDateField.getValue();
        String address = employeeAddressField.getText();
        String email = employeeEmailField.getText();
        String salary = employeeSalaryField.getText();
        String department = employeeDepartmentField.getText();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDateString = birthDate.format(formatter);

        double salaryDouble = Double.parseDouble(salary);

        if (firstName.isEmpty() || lastName.isEmpty() || birthDate == null || address.isEmpty() || email.isEmpty() || salary.isEmpty() || department.isEmpty()) {
            statusLabel.setText("Bitte alle Felder ausfüllen.");
        } else {
            safeToDatabank(firstName, lastName, birthDateString, address, email, department, salaryDouble);
            statusLabel.setText("Mitarbeiter hinzugefügt: " + firstName + " " + lastName);
            clearFields();
        }
    }

    private void safeToDatabank(String fistname, String lastname, String birthdate, String adress, String email, String department, double salary){
        String hiringdate = Data_Processing.CurrentDate();
        Employee newEmployee = new Employee(fistname, lastname, birthdate, adress, email, department, salary, hiringdate);
        newEmployee.saveToDatabase();
    }

    private void clearFields() {
        employeeFirstNameField.clear();
        employeeLastNameField.clear();
        employeeBirthDateField.setValue(null);
        employeeAddressField.clear();
        employeeEmailField.clear();
        employeeSalaryField.clear();
        employeeDepartmentField.clear();
    }
}
