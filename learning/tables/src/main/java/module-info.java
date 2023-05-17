module com.example.tables {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tables to javafx.fxml;
    exports com.example.tables;
}