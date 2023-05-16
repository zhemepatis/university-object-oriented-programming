package com.example.ex3.controllers.sendController;

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

public class SendControllerForm {
    @FXML
    TextField colorField;
    @FXML
    TextField printField;

    @FXML
    public void submitData(ActionEvent e) throws IOException {
        String mugColor = colorField.getText();
        String mugPrint = printField.getText();
        TeaMug mug = new TeaMug(mugColor, mugPrint);

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/ex3/sendController/send-controller-win.fxml")));
        SendControllerWin controller = new SendControllerWin();
        controller.setMug(mug);
        loader.setController(controller);
        Parent root = loader.load();

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.setUserData(mug);
        window.setScene(scene);
        window.show();
    }
}
