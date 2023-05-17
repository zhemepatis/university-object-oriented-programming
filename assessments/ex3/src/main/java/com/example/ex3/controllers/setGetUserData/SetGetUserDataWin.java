package com.example.ex3.controllers.setGetUserData;

import com.example.ex3.mugs.TeaMug;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SetGetUserDataWin {
    @FXML
    Label detailsLabel;
    @FXML
    Button detailsButton;

    @FXML
    public void showDetails(ActionEvent e) {
        Scene currScene = detailsButton.getScene();
        TeaMug mug = (TeaMug) currScene.getUserData();

        detailsLabel.setMaxWidth(400);
        detailsLabel.setWrapText(true);
        detailsLabel.setText(mug.getDetails());
    }
}
