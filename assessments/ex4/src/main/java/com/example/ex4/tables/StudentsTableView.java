package com.example.ex4.tables;

import com.example.ex4.Student;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class StudentsTableView extends Table<Student> {
    TableColumn<Student, String> firstNameCol, secondNameCol;
    TableColumn<Student, Integer> groupCol;

    public StudentsTableView(TableView<Student> table) {
        this.table = table;

        // GENERAL SETTINGS
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(true);

        // MODIFYING COLUMNS
        firstNameCol = new TableColumn<>("First name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(e -> {
            Student student = e.getRowValue();
            student.setFirstName(e.getNewValue());
        });

        secondNameCol = new TableColumn<>("Second name");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        secondNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        secondNameCol.setOnEditCommit(e -> {
            Student student = e.getRowValue();
            student.setSecondName(e.getNewValue());
        });

        groupCol = new TableColumn<>("Group");
        groupCol.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        groupCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        groupCol.setOnEditCommit(e -> {
            Student student = e.getRowValue();
            student.setGroupId(e.getNewValue());
        });

        // ADDING COLUMNS
        table.getColumns().add(firstNameCol);
        table.getColumns().add(secondNameCol);
        table.getColumns().add(groupCol);
    }

    @Override
    public void add(Student object) {
        table.getItems().add(object);  // adds data to the table
    }
}
