package com.example.ex5;

import javafx.scene.control.TableView;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Future;

public class DataReader implements Runnable {
    private InputStream in;

    private TableView<Person> table;

    public DataReader(TableView<Person> table) {
        this.table = table;

        File inputFile = new File("src/main/resources/com/example/ex5/mock_data.csv");
        try {
            in = new BufferedInputStream(new FileInputStream(inputFile));
        } catch(FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    String readLine() {
        String line = "";

        while(true) {
            try {
                byte character = (byte) in.read();
                if((char) character == '\n')
                    break;
                else
                    line += (char) character;
            } catch(IOException exception) {
                exception.printStackTrace();
            }
        }

        return line;
    }

    @Override
    public void run() {
        String line = readLine();   // skipping first line

        while((line = readLine()) != null) {
            String[] lineDetails = line.split(";");

            Person newPerson = new Person();
            newPerson.setFirstName(lineDetails[0]);
            newPerson.setSecondName(lineDetails[1]);
            newPerson.setEmail(lineDetails[2]);
            newPerson.setImageLink(lineDetails[3]);
            newPerson.setIp(lineDetails[4]);

            table.getItems().add(newPerson);
        }


    }

}
