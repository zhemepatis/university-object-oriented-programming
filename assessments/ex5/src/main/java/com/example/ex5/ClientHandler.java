package com.example.ex5;

import com.example.ex5.chatUtilities.User;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private String username;
    private int roomPort;

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            clientHandlers.add(this);

            String data = in.readLine();
            username = data.split("~")[1];
            roomPort = Integer.parseInt(data.split("~")[0]);
            broadcastMessage("server-message~" + roomPort + "~" + username + " has arrived.");
        } catch (IOException e) {
            closeEverything(socket, in, out);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(socket.isConnected()) {   // listening for messages from client
            try {
                String msg = in.readLine();
                System.out.println(msg);

                broadcastMessage(msg);
            } catch (IOException e) {
                closeEverything(socket, in, out);
                break;
            }
        }
    }

    public void broadcastMessage(String msg) {
        for(ClientHandler clientHandler : clientHandlers) {
            try {
                String roomPort = msg.split("~")[1];
                if(clientHandler.getRoomPort() != Integer.parseInt(roomPort)) {
                    continue;
                }

                String command = msg.split("~")[0];
                String sender = "";
                String msgContent = "";

                switch(command) {
                    case "user-message":
                        sender = msg.split("~")[2];
                        if(sender.equals(clientHandler.getUsername()))
                            sender = "You";
                        msgContent = msg.split("~")[3];
                        break;
                    case "server-message":
                        sender = "SERVER";
                        msgContent = msg.split("~")[2];
                        break;
                    default:
                        break;
                }

                clientHandler.out.write("user-message~"+roomPort+"~"+sender+"~"+msgContent);
                clientHandler.out.newLine();
                clientHandler.out.flush();
            } catch (IOException e) {
                closeEverything(socket, in, out);
            }
        }
    }

    public int getRoomPort() {
        return roomPort;
    }

    public String getUsername() {
        return username;
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("server-message~user has left the chat.");
    }

    public void closeEverything(Socket socket, BufferedReader in, BufferedWriter out) {
        removeClientHandler();

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
}
