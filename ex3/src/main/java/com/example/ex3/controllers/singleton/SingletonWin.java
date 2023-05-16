package com.example.ex3.controllers.singleton;

import com.example.ex3.TeaMugSingleton;
import com.example.ex3.mugs.TeaMug;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SingletonWin {
    @FXML
    Label detailsLabel;
    @FXML
    Button detailsButton;

    @FXML
    public void showDetails(ActionEvent e) {
        TeaMug mug = TeaMugSingleton.getInstance().getMug();

        detailsLabel.setMaxWidth(400);
        detailsLabel.setWrapText(true);
        detailsLabel.setText(mug.getDetails());
    }
}
