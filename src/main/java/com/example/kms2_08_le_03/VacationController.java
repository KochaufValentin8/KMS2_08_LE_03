package com.example.kms2_08_le_03;


import com.example.kms2_08_le_03.Classes.ShowTable;
import com.example.kms2_08_le_03.Classes.Vacation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VacationController {

    @FXML
    private TextField employeeIDField;

    @FXML
    private DatePicker startVacationField;

    @FXML
    private DatePicker endVacationField;

    @FXML
    private Label statusLabel;


    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;


    @FXML
    private void handleAddVacation() {
        String employeeIDString = employeeIDField.getText();

        LocalDate startVacationDate = startVacationField.getValue();
        LocalDate endVacationDate = endVacationField.getValue();


        List<Integer> idList = ShowTable.GetIds();

        try {
            int id = Integer.parseInt(employeeIDString);
            if (startVacationDate == null || endVacationDate == null || employeeIDString == "") {
                statusLabel.setText("Bitte alle Felder ausfüllen.");
                showAlert("Fehler!", "Bitte alle Felder ausfüllen!");
            }
            if (!idList.contains(id)){
                    statusLabel.setText("Bitte Gültige ID eingeben!");
                    showAlert("Fehler!", "Bitte Gültige ID eingeben!");
                }
            if (startVacationDate.isAfter(endVacationDate)){
                statusLabel.setText("Der Urlaubsstart darf nicht nach dem Urlaubsende sein!");
                showAlert("Fehler!", "Der Urlaubsstart darf nicht nach dem Urlaubsende sein!");
            }
            String startVac = DateFormatter(startVacationDate);
            String endVac = DateFormatter(endVacationDate);

            if (endVac == "" || endVac == null || startVac == "" || startVac == null){
                statusLabel.setText("Bitte Gültiges Datum eingeben!");
                showAlert("Fehler!", "Bitte Gültiges Datum eingeben!");
            }


            else{



                Vacation VacationToAddToDatabank = new Vacation(id, startVac, endVac);
                VacationToAddToDatabank.saveToDatabase();
                statusLabel.setText("Urlaub eingetragen: Mitarbeiter ID: " + employeeIDString + "Beginn: " + startVacationDate + "Ende: " + endVacationDate);
                clearFields();}

        } catch (NumberFormatException e) {
            statusLabel.setText("Ungültige ID.");
            showAlert("Fehler!", "Bitte Gültige ID eingeben.");
        }

    }

    private String DateFormatter(LocalDate localDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = localDate.format(formatter);
            return formattedDate;
        } catch (Exception e) {
            return null;
        }
    }

    private void clearFields() {
        employeeIDField.clear();
        startVacationField.setValue(null);
        endVacationField.setValue(null);

    }


    @FXML
    private void handleCloseWindow() {
        // Schließt das aktuelle Fenster
        closeButton.getScene().getWindow().hide();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

}
