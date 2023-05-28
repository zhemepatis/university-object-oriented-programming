package com.example.ex5.controllers;

import com.example.ex5.Client;
import com.example.ex5.chatUtilities.Message;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatController {
    @FXML
    private TableView<Message> table;
    @FXML
    private TableColumn<Message, String> msgCol;
    @FXML
    private TableColumn<Message, String> senderCol;
    @FXML
    private TextField msgField;
    @FXML
    private Button sendButton;

    private Client client;

    public void initialize() {
        client.setTable(table);
        client.listenForMessages();

        senderCol.setCellValueFactory(new PropertyValueFactory<>("sender"));
        msgCol.setCellValueFactory(new PropertyValueFactory<>("contents"));

        sendButton.setOnAction(event -> messageHandle());

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void messageHandle() {
        // saving message, clearing text field
        Message msg = new Message(msgField.getText(), client.getUser());
        msgField.clear();
        msgField.requestFocus();

        // message handling on server side
        client.sendMessage(msg);
    }
}
