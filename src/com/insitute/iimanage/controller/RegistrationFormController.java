package com.insitute.iimanage.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class RegistrationFormController {
    public AnchorPane context;
    public TextField txtRegId;
    public TextField txtStudent;
    public ComboBox cmbCourse;
    public RadioButton rdbPaid;
    public ToggleGroup payment;
    public RadioButton rdbPending;
    public TextField txtSearch;
    public TableView tblReg;
    public TableColumn colId;
    public TableColumn colStudent;
    public TableColumn colReg;
    public TableColumn colCourse;
    public TableColumn colPayment;
    public TableColumn colOption;

    public void newRegOnAction(ActionEvent actionEvent) {

    }

    public void backToHomeOnAction(ActionEvent actionEvent) {

    }

    public void saveRegOnAction(ActionEvent actionEvent) {

    }
}
