module com.example.ex4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ex4 to javafx.fxml;
    exports com.example.ex4;
    opens com.example.ex4.controllers to javafx.fxml;
    exports com.example.ex4.controllers;
}