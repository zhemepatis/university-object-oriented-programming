package com.example.ex5.chatUtilities;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private static ArrayList<Room> rooms;

    private int port;
    private List<User> users;
    private List<Message> messages;

    public Room() {
        users = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addMessage(Message msg) {
        messages.add(msg);
    }

    public void removeMessage(Message msg) {
        messages.remove(msg);
    }
}
