package com.example.kms2_08_le_03;

import com.example.kms2_08_le_03.Classes.ShowTable;
import com.example.kms2_08_le_03.Classes.Training;
import com.example.kms2_08_le_03.Classes.Vacation;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TrainingController {

    @FXML
    private TextField employeeIDField;

    @FXML
    private TextField trainingNameField;

    @FXML
    private DatePicker dateOfTrainingField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Button addButton;

    @FXML
    private void handleAddVacation() {
        String employeeIDString = employeeIDField.getText();
        String trainingName = trainingNameField.getText();
        LocalDate dateOfTraining = dateOfTrainingField.getValue();
        int trainingNameLengh = trainingName.length();

        List<Integer> idList = ShowTable.GetIds();
        String trainingDate = DateFormatter(dateOfTraining);

        try {
            int id = Integer.parseInt(employeeIDString);
            if (trainingName == null || trainingNameLengh < 2 || dateOfTraining == null || employeeIDString == "" || trainingName == "") {
                statusLabel.setText("Bitte alle Felder ausfüllen.");
                showAlert("Fehler!", "Bitte alle Felder ausfüllen!");
            }

            else if (!idList.contains(id)){
                    statusLabel.setText("Bitte Gültige ID eingeben!");
                showAlert("Fehler!", "Bitte Gültige ID eingeben!");
                }

            else if (trainingDate == "" || trainingDate == null){
                statusLabel.setText("Bitte Gültiges Datum eingeben!");
                showAlert("Fehler!", "Bitte Gültiges Datum eingeben!");
            }
            else{
                Training.AddTraining(id, trainingName, trainingDate);
                statusLabel.setText("Schulung: " + trainingName + " für :" + employeeIDString + " am: " + trainingDate + " eingetragen");
                clearFields();}

        } catch (NumberFormatException e) {
            statusLabel.setText("Ungültige ID.");
            showAlert("Fehler!", "Bitte Gültige ID eingeben!");
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
        trainingNameField.clear();
        dateOfTrainingField.setValue(null);

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
