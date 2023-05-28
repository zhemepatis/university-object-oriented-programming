package com.example.ex4.groups;

import com.example.ex4.Student;

import java.util.List;

public abstract class Group<T> {
    protected int groupId;
    protected List<T> list;

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getGroupId() {
        return groupId;
    }

    public List<T> getList() {
        return list;
    }

    abstract void addToGroup(T element);
    abstract void removeFromGroup(int elementNum);
}
