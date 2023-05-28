module com.example.ex9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ex9 to javafx.fxml;
    exports com.example.ex9;
}