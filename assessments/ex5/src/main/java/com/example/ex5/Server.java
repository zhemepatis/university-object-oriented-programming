package com.example.ex5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class Server {
    private final ServerSocket server;

    public Server(ServerSocket server) {
        this.server = server;
    }

    void startServer() {
        try {
            while(!server.isClosed()) {
                Socket clientSocket = server.accept();  // waits for client to join
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                Thread thread = new Thread(clientHandler);  // thread for handling client connections
                thread.start(); // runs runable object "run" method

                System.out.println("new connection established.");
            }
        }
        catch (IOException e) {
            closeServerSocket();
        }
    }

    void closeServerSocket() {
        try {
            if(server != null) {
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
