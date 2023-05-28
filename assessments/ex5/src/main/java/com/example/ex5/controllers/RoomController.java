package com.example.ex5.controllers;

import com.example.ex5.Client;
import com.example.ex5.chatUtilities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class RoomController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField roomPortField;

    Client client;

    @FXML
    public void roomHandle(ActionEvent e) throws IOException {
        User user = new User(usernameField.getText());
        int roomPort = Integer.parseInt(roomPortField.getText());

        // connecting to the server
        connectToServer(user, roomPort);

        ChatController cc = new ChatController();
        cc.setClient(client);

        // showing chat window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ex5/views/chat-view.fxml"));
        loader.setController(cc);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("room: " + roomPortField.getText());
        stage.setOnCloseRequest(event -> {
            client.closeConnection();
        });
        stage.setScene(scene);
        stage.show();
    }

    public void connectToServer(User user, int roomPort) {
        try {
            Socket socket = new Socket("localhost", 8080);
            client = new Client(socket, user, roomPort);
        } catch (IOException e) {
            System.out.println("unable to connect to server");
            e.printStackTrace();
        }
    }
}
