package com.insitute.iimanage.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class StudentFormController {
    public AnchorPane context;
    public TextField txtStudentID;
    public TextField txtFullName;
    public DatePicker txtDob;
    public TextField txtAddress;
    public Button btnSaveStudnet;
    public TextField txtSearch;
    public TableView tblStudent;
    public TableColumn colStudnetID;
    public TableColumn colFullName;
    public TableColumn colDob;
    public TableColumn colAddress;
    public TableColumn colOption;

    public void newStudentOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
    }
}
