module com.example.ex5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ex5 to javafx.fxml;
    exports com.example.ex5;
    exports com.example.ex5.chatUtilities;
    opens com.example.ex5.chatUtilities to javafx.fxml;
    exports com.example.ex5.controllers;
    opens com.example.ex5.controllers to javafx.fxml;
}