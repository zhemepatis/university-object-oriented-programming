package com.example.tables;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = new AnchorPane();

        // CREATING TABLE
        TableView<Person> table = new TableView<Person>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(500);

        TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("First name");   //
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

        TableColumn<Person, String> secondNameCol = new TableColumn<Person, String>("Second name");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("secondName"));

        TableColumn<Person, Integer> ageCol = new TableColumn<Person, Integer>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));

        table.getColumns().add(firstNameCol);
        table.getColumns().add(secondNameCol);
        table.getColumns().add(ageCol);

        root.getChildren().add(table);  // adds table to the pane

        // ADDING SOME DATA
        table.getItems().add(new Person("Bill", "Gates", 72));
        table.getItems().add(new Person("Mark", "Zuckerberg", 43));
        table.getItems().add(new Person("Denis", "Ritchie", 81));

        // SHOWING PANE
        stage.setResizable(false);  // makes stage non-resizable
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}