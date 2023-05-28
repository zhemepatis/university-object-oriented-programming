package com.example.ex4.tables;

import javafx.scene.control.TableView;

public abstract class Table<T> {
    public TableView<T> table;

    abstract void add(T object);

    public void delete(int row) {
        table.getItems().remove(row);
    }
}
