package com.example.ex4.controllers;

import com.example.ex4.Student;
import com.example.ex4.tables.StudentsTableView;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentsList {
    @FXML
    TextField firstNameField, secondNameField, groupField;
    @FXML
    TableView<Student> table;

    StudentsTableView studentsTableView;

    @FXML
    public void initialize() {
        studentsTableView = new StudentsTableView(table);
    }

    @FXML
    void addStudent() {
        // ADDING DATA TO THE TABLE
        Student student = new Student(firstNameField.getText(), secondNameField.getText(), Integer.parseInt(groupField.getText()));
        studentsTableView.add(student);

        // RESETTING TXT FIELDS
        firstNameField.clear();
        secondNameField.clear();
        groupField.clear();
    }

    @FXML
    void deleteStudent() {
        // DELETING ROW
        int row = studentsTableView.table.getSelectionModel().getSelectedIndex();
        studentsTableView.delete(row);

        // REMOVING SELECTION
        studentsTableView.table.getSelectionModel().clearSelection();
    }
}
