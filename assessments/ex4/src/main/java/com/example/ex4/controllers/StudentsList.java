package com.example.ex4.controllers;

import com.example.ex4.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class StudentsList {
    @FXML
    TextField firstNameField, secondNameField, groupField;
    @FXML
    TableView <Student> tableView;
    @FXML
    TableColumn<Student, String> firstNameCol, secondNameCol;
    @FXML
    TableColumn<Student, Integer> groupCol;

    @FXML
    public void initialize() {  // runs after controller constructor and has access to all FXML components when the constructor does not
        tableView.setEditable(true);

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        secondNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("secondName"));
        secondNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        groupCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
        groupCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    @FXML
    void add() {
        // ADDING DATA
        Student student = new Student(firstNameField.getText(), secondNameField.getText(), Integer.parseInt(groupField.getText()));
        tableView.getItems().add(student);  // adds data to the table

        // RESETTING TXT FIELDS
        firstNameField.clear();
        secondNameField.clear();
        groupField.clear();
    }
}
