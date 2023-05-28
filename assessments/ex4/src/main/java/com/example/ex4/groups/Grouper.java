package com.example.ex4.groups;

import java.util.List;

public class Grouper extends Group<StudentGroup> {
    int groupNum = 0;

    public Grouper() {
        setGroupId(groupNum+1);
    }

    @Override
    void addToGroup(StudentGroup element) {
        list.add(element);
    }

    @Override
    void removeFromGroup(int elementNum) {
        list.remove(elementNum);
    }
}
