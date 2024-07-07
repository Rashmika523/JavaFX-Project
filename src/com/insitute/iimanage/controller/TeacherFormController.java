package com.insitute.iimanage.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TeacherFormController {
    public AnchorPane context;
    public TextField textTeacherID;
    public TextField txtFullName;
    public TextField txtAddress;
    public Button btnSaveTeacher;
    public TextField txtSearch;
    public TableView tblTeacher;
    public TableColumn colTeacherId;
    public TableColumn colFullName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;
    public TextField txtContact;

    public void newTeacherOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }

    public void saveTeacherOnAction(ActionEvent actionEvent) {
    }
}
