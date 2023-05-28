module com.example.ex5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ex5 to javafx.fxml;
    exports com.example.ex5;
}