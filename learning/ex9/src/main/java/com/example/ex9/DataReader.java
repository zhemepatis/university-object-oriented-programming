package com.example.ex9;

import javafx.collections.ObservableList;

import java.io.*;

public class DataReader implements Runnable {
    private InputStream in;

    private ObservableList<Person> list;

    public DataReader(ObservableList<Person> list) {
        this.list = list;

        File inputFile = new File("src/main/resources/com/example/ex9/mock_data.csv");
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

            list.add(newPerson);
        }
    }
}