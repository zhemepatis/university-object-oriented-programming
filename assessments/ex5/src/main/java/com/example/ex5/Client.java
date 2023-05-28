package com.example.ex5;

import com.example.ex5.chatUtilities.Message;
import com.example.ex5.chatUtilities.Room;
import com.example.ex5.chatUtilities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private Socket socket;

    private BufferedReader in;
    private BufferedWriter out;

    private User user;
    private Room room;
    private TableView<Message> table;

    public Client(Socket socket, User user, int roomPort) {
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.user = user;
            room = new Room();
            this.room.setPort(roomPort);

            out.write(roomPort + "~" + user.getUsername());  // sends client handler room port and username
            out.newLine();
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setTable(TableView<Message> table) {
        this.table = table;
    }

    public User getUser() {
        return user;
    }

    public void sendMessage(Message msg) {  // sends message for client handler
        try{
            out.write("user-message~" + room.getPort() + "~" + msg.getSender().getUsername() + "~" + msg.getContents());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            closeEverything(socket, in, out);
        }
    }

    public void listenForMessages() {   // listens for messages from server
        new Thread(() -> {
            while(socket.isConnected()) {
                try {
                    String data = in.readLine();    // reading message broadcasted from client handler

                    // displaying message
                    String[] messageString = data.split("~");
                    Message newMessage = new Message(messageString[3], new User(messageString[2]));
                    table.getItems().add(newMessage);
                } catch(IOException e) {
                    closeEverything(socket,in, out);
                    break;
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            if(in != null)
                in.close();
            if(out != null)
                out.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        closeEverything(socket, in, out);
    }
}