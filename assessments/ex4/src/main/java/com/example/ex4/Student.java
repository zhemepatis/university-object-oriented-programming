package com.example.ex4;

public class Student {
    private String firstName, secondName;
    private Integer group;

    public Student(String firstName, String secondName, Integer group) {
        setFirstName(firstName);
        setSecondName(secondName);
        setGroup(group);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Integer getGroup() {
        return group;
    }
}
