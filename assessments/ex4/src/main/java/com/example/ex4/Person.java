package com.example.ex4;

public class Person {
    private String firstName, secondName;

    public Person(String firstName, String secondName) {
        setFirstName(firstName);
        setSecondName(secondName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
