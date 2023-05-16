module com.example.ex3 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.ex3 to javafx.fxml;
    exports com.example.ex3;
    exports com.example.ex3.controllers;
    opens com.example.ex3.controllers to javafx.fxml;
    exports com.example.ex3.controllers.sendController;
    opens com.example.ex3.controllers.sendController to javafx.fxml;
    exports com.example.ex3.controllers.setGetUserData;
    opens com.example.ex3.controllers.setGetUserData to javafx.fxml;
    exports com.example.ex3.controllers.singleton;
    opens com.example.ex3.controllers.singleton to javafx.fxml;
}