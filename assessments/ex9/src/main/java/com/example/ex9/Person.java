package com.example.ex9;

public class Person {
    private String firstName, secondName, email, ip, imageLink;

    public Person() {
        firstName = "";
        secondName = "";
        email = "";
        ip = "";
        imageLink = "";
    }

    public Person(String firstname, String secondName, String email, String ip, String imageLink) {
        this.firstName = firstname;
        this.secondName = secondName;
        this.email = email;
        this.ip = ip;
        this.imageLink = imageLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String toString() {
        return firstName + ";"+secondName + ";" + email + ";" + imageLink + ";" + ip;
    }
}
