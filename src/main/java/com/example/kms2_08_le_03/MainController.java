package com.example.kms2_08_le_03;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainController {

    @FXML
    private Button mitarbeiterButton;

    @FXML
    private Button adminButton;

    // Öffnet das Mitarbeitermenü
    @FXML
    private void openMitarbeiterMenu() throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("mitarbeiter_view.fxml"));
        stage.setTitle("Mitarbeitermenü");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    // Öffnet das Adminmenü
    @FXML
    private void openAdminMenu() throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("admin_view.fxml"));
        stage.setTitle("Adminmenü");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }
}
