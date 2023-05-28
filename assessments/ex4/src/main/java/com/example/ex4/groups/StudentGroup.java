package com.example.ex4.groups;

import com.example.ex4.Student;

public class StudentGroup extends Group<Student> {

    public StudentGroup(int groupId) {
        setGroupId(groupId);
    }

    @Override
    void addToGroup(Student element) {
        list.add(element);
    }

    @Override
    void removeFromGroup(int elementNum) {
        list.remove(elementNum);
    }
}
