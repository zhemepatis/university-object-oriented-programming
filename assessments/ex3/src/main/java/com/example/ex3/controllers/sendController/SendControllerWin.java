package com.example.ex3.controllers.sendController;

import com.example.ex3.mugs.TeaMug;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SendControllerWin {
    @FXML
    Label detailsLabel;

    TeaMug mug;

    @FXML
    public void initialize() {
        detailsLabel.setMaxWidth(400);
        detailsLabel.setWrapText(true);
        detailsLabel.setText(this.mug.getDetails());
    }

    public void setMug(TeaMug mug) {
        this.mug = mug;
    }
}
