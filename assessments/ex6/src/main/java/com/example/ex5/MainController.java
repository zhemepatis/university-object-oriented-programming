package com.example.ex5;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.*;

public class MainController {
    @FXML
    TableView<Person> dataTable;
    @FXML
    TableView<Person> filesTable;
    @FXML
    TableColumn <Person, String> dataCol;
    @FXML
    TableColumn <Person, String> fileCol;

    Thread thread;

    public void initialize() {
        dataCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));
        fileCol.setCellValueFactory(cellData -> {
            Person person = cellData.getValue();
            String newData = getFileName(person);
            return new SimpleStringProperty(newData);
        });

        thread = new Thread(new DataReader(dataTable));
        thread.start();
    }

    @FXML
    void createFile() {
        Person selectedData = dataTable.getSelectionModel().getSelectedItem();

        if(selectedData == null)
            return;

        String filename = getFileName(selectedData);
        File newFile = new File("src/main/resources/com/example/ex5/data/"+filename);

        try {
            if(newFile.createNewFile()) {
                System.out.println("File " + filename + " created.");

                OutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
                byte[] bytes = selectedData.toString().getBytes();

                out.write(bytes);
                out.flush();

                out.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        dataTable.getItems().remove(selectedData);
        dataTable.getSelectionModel().select(null);
        filesTable.getItems().add(selectedData);
    }

    String getFileName(Person person) {
        // setting first three name letters
        String filename = "";
        String firstName = person.getFirstName();
        filename += firstName.substring(0, 3);

        // setting first three surname letters
        String secondName = person.getSecondName();
        filename += secondName.substring(0, 3);

        // setting last  digits of ip
        String ip = person.getIp();
        String[] ipNumbers = ip.split("\\.");
        filename += ipNumbers[ipNumbers.length-2]; // -2 because of \n character

        // adding extension
        filename += ".txt";

        return filename;
    }

    @FXML
    void deleteFile() {
        Person selectedData = filesTable.getSelectionModel().getSelectedItem();

        if(selectedData == null)
            return;

        String filename = getFileName(selectedData);
        File newFile = new File("src/main/resources/com/example/ex5/data/"+filename);

        try {
            if(newFile.delete())
                System.out.println("File " + filename + " deleted.");
            else
                System.out.println("Something went wrong when deleting" + newFile.getName() + ".");

        } catch(Exception e) {
            e.printStackTrace();
        }

        filesTable.getItems().remove(selectedData);
        filesTable.getSelectionModel().select(null);
        dataTable.getItems().add(selectedData);
    }

    @FXML
    void deleteAllFiles() {
        int rowNum = filesTable.getItems().size();

        for(int i = 0; i < rowNum; ++i) {
            filesTable.getSelectionModel().select(0);
            deleteFile();
        }
    }
}
