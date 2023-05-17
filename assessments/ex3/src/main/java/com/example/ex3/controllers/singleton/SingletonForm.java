package com.example.ex3.controllers.singleton;

import com.example.ex3.TeaMugSingleton;
import com.example.ex3.mugs.TeaMug;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SingletonForm {
    @FXML
    TextField colorField;
    @FXML
    TextField printField;

    @FXML
    public void submitData(ActionEvent e) throws IOException {
        String mugColor = colorField.getText();
        String mugPrint = printField.getText();
        TeaMug mug = new TeaMug(mugColor, mugPrint);

        TeaMugSingleton singleton = TeaMugSingleton.getInstance();
        singleton.setMug(mug);

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/ex3/singleton/singleton-win.fxml")));
        Parent root = loader.load();

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
