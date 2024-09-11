package com.example.kms2_08_le_03;

import com.example.kms2_08_le_03.Classes.Evaluation;
import com.example.kms2_08_le_03.Classes.ShowTable;
import com.example.kms2_08_le_03.Classes.Training;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EvaluationController {

    @FXML
    private TextField employeeIDField;

    @FXML
    private TextField evaluaionGradeField;

    @FXML
    private Label statusLabel;


    @FXML
    private Button addButton;

    @FXML
    private void handleAddVacation() {
        String employeeIDString = employeeIDField.getText();
        String evaluaionGrade = evaluaionGradeField.getText();


        List<Integer> idList = ShowTable.GetIds();


        try {
            int id = Integer.parseInt(employeeIDString);
            int numOfEv = Integer.parseInt(evaluaionGrade);
            if (evaluaionGrade == null || evaluaionGradeField  == null || employeeIDString == "" || evaluaionGrade == "") {
                statusLabel.setText("Bitte alle Felder ausfüllen.");
            }

            else if (!idList.contains(id)){
                    statusLabel.setText("Bitte Gültige ID eingeben!");
                }

            else if (numOfEv > 5 || numOfEv < 1){
                statusLabel.setText("Bitte Gültige Bewertung eingeben!");
            }


            else{
                Evaluation.AddEvaluation(id, numOfEv);
                statusLabel.setText("Bewertungsnote: " + evaluaionGrade + " für : ID:" + employeeIDString + " eingetragen");
                clearFields();}

        } catch (NumberFormatException e) {
            statusLabel.setText("Ungültige ID.");
        }

    }



    private void clearFields() {
        employeeIDField.clear();
        evaluaionGradeField.clear();


    }
}
