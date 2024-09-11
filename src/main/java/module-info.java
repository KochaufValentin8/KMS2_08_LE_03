module com.example.kms2_08_le_03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kms2_08_le_03 to javafx.fxml;
    exports com.example.kms2_08_le_03;
}