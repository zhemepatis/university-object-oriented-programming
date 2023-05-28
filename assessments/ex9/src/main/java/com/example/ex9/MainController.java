package com.example.ex9;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController {
    @FXML
    private TableView<Person> dataTable;
    @FXML
    private TableColumn<Person, String> firstNameCol;
    @FXML
    private TableColumn<Person, String> secondNameCol;
    @FXML
    private TableColumn<Person, String> emailCol;
    @FXML
    private TableColumn<Person, String> imageLinkCol;
    @FXML
    private TableColumn<Person, String> ipCol;
    @FXML
    private TextField firstNameFilterField;
    @FXML
    private TextField secondNameFilterField;
    @FXML
    private TextField emailFilterField;
    @FXML
    private TextField imageLinkFilterField;
    @FXML
    private TextField ipFilterField;
    @FXML
    private ComboBox<Person> preSavedFiltersComboBox;

    private Comparator<Person> personComparator;

    private ObservableList<Person> dataList;
    private ObservableList<Person> comboBoxList;
    private boolean isFiltered = false;

    public void initialize() throws InterruptedException {
        // setting column values
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        imageLinkCol.setCellValueFactory(new PropertyValueFactory<>("imageLink"));
        ipCol.setCellValueFactory(new PropertyValueFactory<>("ip"));

        // setting observable list
        dataList = FXCollections.observableArrayList();
        dataList.addListener((ListChangeListener<Person>) change -> applySort());
        dataTable.setItems(dataList);

        comboBoxList = FXCollections.observableArrayList();
        preSavedFiltersComboBox.setItems(comboBoxList);

        // runs thread that loads the data
        Thread thread = new Thread(new DataReader(dataList));
        thread.start();

        // setting person comparators
        setPersonComparatorToAscending();
    }

    void applySort() {
        Stream<Person> sortedData;
        if(isFiltered)
            sortedData = dataTable.getItems().stream().sorted(personComparator);
        else
            sortedData = dataList.stream().sorted(personComparator);

        dataTable.setItems(sortedData.collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    @FXML
    void setPersonComparatorToAscending() {
        personComparator = (person1, person2) -> {
            int comparisonResult = person1.getFirstName().compareTo(person2.getFirstName());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person1.getSecondName().compareTo(person2.getSecondName());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person1.getEmail().compareTo(person2.getEmail());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person1.getImageLink().compareTo(person2.getImageLink());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person1.getIp().compareTo(person2.getIp());
            return comparisonResult;
        };

        applySort();
    }

    @FXML
    void setPersonComparatorToDescending() {
        personComparator = (person1, person2) -> {
            int comparisonResult = person2.getFirstName().compareTo(person1.getFirstName());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person2.getSecondName().compareTo(person1.getSecondName());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person2.getEmail().compareTo(person1.getEmail());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person2.getImageLink().compareTo(person1.getImageLink());
            if(comparisonResult != 0)
                return comparisonResult;

            comparisonResult = person2.getIp().compareTo(person1.getIp());
            return comparisonResult;
        };

        applySort();
    }

    @FXML
    public void applyFilters() {
        // getting filters
        String firstNameFilter = firstNameFilterField.getText().toLowerCase();
        String secondNameFilter = secondNameFilterField.getText().toLowerCase();
        String emailFilter = emailFilterField.getText().toLowerCase();
        String imageLinkFilter = imageLinkFilterField.getText().toLowerCase();
        String ipFilter = ipFilterField.getText().toLowerCase();

        // showing filtered data
        Stream<Person> filteredData = dataList.stream().filter(person -> {
            return person.getFirstName().toLowerCase().contains(firstNameFilter)
                    && person.getSecondName().toLowerCase().contains(secondNameFilter)
                    && person.getEmail().toLowerCase().contains(emailFilter)
                    && person.getImageLink().toLowerCase().contains(imageLinkFilter)
                    && person.getIp().toLowerCase().contains(ipFilter);
        });
        dataTable.setItems(filteredData.sorted(personComparator).collect(Collectors.toCollection(FXCollections::observableArrayList)));

        isFiltered = true;
    }

    @FXML
    void saveFilter() {
        // getting filters
        String firstNameFilter = firstNameFilterField.getText();
        String secondNameFilter = secondNameFilterField.getText();
        String emailFilter = emailFilterField.getText();
        String imageLinkFilter = imageLinkFilterField.getText();
        String ipFilter = ipFilterField.getText();

        Person person = new Person(firstNameFilter, secondNameFilter, emailFilter, imageLinkFilter, ipFilter);
        comboBoxList.add(person);
        preSavedFiltersComboBox.getSelectionModel().select(person);
    }

    @FXML
    void applyPreSavedFilter() {
        Person person = preSavedFiltersComboBox.getSelectionModel().getSelectedItem();

        if(person == null)
            return;

        firstNameFilterField.setText(person.getFirstName());
        secondNameFilterField.setText(person.getSecondName());
        emailFilterField.setText(person.getEmail());
        imageLinkFilterField.setText(person.getImageLink());
        ipFilterField.setText(person.getIp());

        applyFilters();
    }

    @FXML
    void clearFilters() {
        firstNameFilterField.clear();
        secondNameFilterField.clear();
        emailFilterField.clear();
        imageLinkFilterField.clear();
        ipFilterField.clear();

        preSavedFiltersComboBox.getSelectionModel().select(null);
        applyFilters();
        isFiltered = false;
    }

    @FXML
    void printPeople() {
        Map<String, List<Person>> personGroups = dataTable.getItems().stream().collect(Collectors.groupingBy(person -> person.getIp().substring(0, 3)));
        personGroups.forEach((ipPrefix, personList) -> {
            System.out.println("IP prefix: " + ipPrefix);
            personList.forEach((person -> System.out.println("\t"+person.getFirstName() + " " + person.getSecondName() + " " + person.getEmail() + " " + person.getImageLink() + " " + person.getIp())));
        });
    }


}