package com.example.ex5.chatUtilities;

public class User {
    private String username;

    public User(String username) {
        setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return username;
    }
}
