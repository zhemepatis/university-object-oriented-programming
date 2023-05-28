package com.example.ex5.chatUtilities;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime date;
    private User sender;
    private String contents;

    public Message(String message, User sender) {
        this.contents = message;
        this.sender = sender;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
