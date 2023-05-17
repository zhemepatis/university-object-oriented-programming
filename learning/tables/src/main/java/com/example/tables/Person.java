package com.example.tables;

public class Person {
    private String firstName;
    private String secondName;
    private int age;

    public Person(String firstName, String secondName, int age) {
        setFirstName(firstName);
        setSecondName(secondName);
        setAge(age);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
