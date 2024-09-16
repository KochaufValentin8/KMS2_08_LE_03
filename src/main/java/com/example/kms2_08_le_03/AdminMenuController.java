package com.example.kms2_08_le_03;
import com.example.kms2_08_le_03.Classes.Payroll;
import com.example.kms2_08_le_03.Classes.ShowTable;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdminMenuController {

    // Überschrift
    @FXML
    private Label adminLabel;

    @FXML
    public void initialize() {
        adminLabel.setText("Adminmenü - Willkommen");
    }

    @FXML
    private void handleAddEmployee() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addEmployee_view.fxml"));
            //stage.setTitle("Mitarbeitermenü");
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
            showAlert("Fehler", "Konnte die Ansicht nicht laden: " + e.getMessage());
        }
    }


    @FXML
    private void handleShowAllEmployees() {
        try {
            System.out.println("Alle Mitarbeiter anzeigen...");
            List<String> allEmployeesInfo = ShowTable.AllEmployees();
            String employeesInfoString = String.join("\n", allEmployeesInfo);
            showAlert("Alle Mitarbeiter anzeigen", employeesInfoString);
        } catch (Exception e) {
            System.out.println("Fehler beim Abrufen der Mitarbeiter: " + e.getMessage());
        }


    }

    @FXML
    private void handlePayroll() {
        System.out.println("Gehaltsabrechnung");
        Map<Integer, Double> idWithSalary = ShowTable.GetIDandSalary();
        int payrollCounter = 0;
        for (Map.Entry<Integer, Double> entry : idWithSalary.entrySet()) {
            payrollCounter ++;
            Integer key = entry.getKey();
            Double value = entry.getValue();
            Payroll.MakePayRoll(key, value);
        }

        showAlert("Gehaltsabrechnung", "Gehaltsabrechnung Abgeschlossen. Es wurden " + payrollCounter + " gehälter Abgerechnet.");
    }

    @FXML
    private void handleVacationEntry() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("vacation_view.fxml"));
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
            showAlert("Fehler", "Konnte die Ansicht nicht laden: " + e.getMessage());
        }
    }

    @FXML
    private void handleTrainingEntry() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("training_view.fxml"));
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
            showAlert("Fehler", "Konnte die Ansicht nicht laden: " + e.getMessage());
        }
    }

    @FXML
    private void handlePerformanceReview() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("evaluation_view.fxml"));
            stage.setScene(new Scene(root, 500, 600));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
            showAlert("Fehler", "Konnte die Ansicht nicht laden: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToMainMenu() {
        Stage stage = (Stage) adminLabel.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}




