package com.example.ex4;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private Integer groupId;
    Map<LocalDate, Boolean> attendance;

    public Student(String firstName, String secondName, Integer groupId) {
        super(firstName, secondName);
        setGroupId(groupId);
        attendance = new HashMap<>();
    }

    public void setGroupId(Integer group) {
        this.groupId = group;
    }

    public void setPresence(LocalDate date, boolean presence) {
        attendance.put(date, presence);
    }

    public Integer getGroupId() {
        return groupId;
    }

    public boolean getPresence(LocalDate date) {
        return attendance.get(date);
    }
}
