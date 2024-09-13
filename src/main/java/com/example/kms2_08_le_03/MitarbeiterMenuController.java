package com.example.kms2_08_le_03;
import com.example.kms2_08_le_03.Classes.EmployeeTimes;
import com.example.kms2_08_le_03.Classes.ShowTable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;


public class MitarbeiterMenuController {


    @FXML
    private Label mitarbeiterLabel;

    @FXML
    private TextField idInput;

    @FXML
    private VBox optionsMenu;

    @FXML
    public void initialize() {
        mitarbeiterLabel.setText("Bitte geben Sie eine ID ein:");
        optionsMenu.setVisible(false); // Menü wird anfangs versteckt
    }

    List<Integer> idList = ShowTable.GetIds();
    EmployeeTimes newEmployeeTimes = new EmployeeTimes();
    int idEmployee;                     //{"ID 0", "startwork 1", "endwork 2", "workdate 3", "startpause 4", "endpause 5"};
    String[] list_Info_newEmployeeTimes = {"0", "0", "0", "0", "0", "0"};



    @FXML
    public void onSubmitId() {
        String input = idInput.getText();
        try {
            int id = Integer.parseInt(input);
            if (idList.contains(id)) { // Überprüfung ob es den Mitarbeiter gibt
                mitarbeiterLabel.setText("Willkommen! Ihre ID ist: " + id);
                idEmployee = id;
                optionsMenu.setVisible(true); // Menü wird nach gültiger Eingabe sichtbar

            } else {
                mitarbeiterLabel.setText("Ungültige ID, bitte erneut versuchen.");
            }
        } catch (NumberFormatException e) {
            mitarbeiterLabel.setText("Ungültige Eingabe, bitte eine Zahl eingeben.");
        }
    }

    @FXML
    public void startArbeit() {
        String[] startWorksString = newEmployeeTimes.startWork();
        list_Info_newEmployeeTimes[3] = startWorksString[0]; // date index 3
        list_Info_newEmployeeTimes[1] = startWorksString[1]; // time index 1
        mitarbeiterLabel.setText("Arbeit begonnen");
    }

    @FXML
    public void startPause() {
        if (list_Info_newEmployeeTimes[1] == "0"){
            showAlert("Falsche eintragung", "Du musst dich zuerst in die Arbeit einschreiben!");
        }
        else {
        String pauseTimeStart = newEmployeeTimes.startPause();
        list_Info_newEmployeeTimes[4] = pauseTimeStart;
        mitarbeiterLabel.setText("Pause begonnen");
        }
    }

    @FXML
    public void endePause() {
        if (list_Info_newEmployeeTimes[4] == "0"){
            showAlert("Falsche eintragung", "Du musst dich zuerst in die Pause einschreiben!");
        }
        else {
            String pauseTimeEnd = newEmployeeTimes.endPause();
            list_Info_newEmployeeTimes[5] = pauseTimeEnd;
            mitarbeiterLabel.setText("Pause beendet");
        }
    }

    @FXML
    public void endeArbeit() {
        if (list_Info_newEmployeeTimes[1] == "0"){
            showAlert("Falsche eintragung", "Du musst dich erst eintragen!");
        }
        else {

            String endWorkTime = newEmployeeTimes.endWork();
            list_Info_newEmployeeTimes[2] = endWorkTime;
            mitarbeiterLabel.setText("Arbeit beendet");
            EmployeeTimes employeeTimesToSend = new EmployeeTimes(idEmployee, list_Info_newEmployeeTimes[1], list_Info_newEmployeeTimes[2], list_Info_newEmployeeTimes[3], list_Info_newEmployeeTimes[4], list_Info_newEmployeeTimes[5]);
            employeeTimesToSend.saveToDatabase();
        }

    }

    @FXML
    public void mainMenu() {
        mitarbeiterLabel.setText("Zurück zum Hauptmenü");
        Stage stage = (Stage) mitarbeiterLabel.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
