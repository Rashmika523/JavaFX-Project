package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

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

    public void initialize(){
        genarateStudentID();
    }


    public void newStudentOnAction(ActionEvent actionEvent) {

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Dashboard");
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {

        Student  student = new Student(
                txtStudentID.getText(),
                txtFullName.getText(),
                Date.from(txtDob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                txtAddress.getText()
        );

        Database.studentTable.add(student);
        genarateStudentID();
        new Alert(Alert.AlertType.INFORMATION,"Student has been Saved...!").show();
        System.out.println(student.toString());
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void genarateStudentID() {

        if(!Database.studentTable.isEmpty()){

            Student lastStudent = Database.studentTable.get(Database.studentTable.size() - 1);
            String stringId = lastStudent.getId();
            String[] split = stringId.split("-");
            String lastIdAsString = split[1];
            int lastIdAsInteger =Integer.parseInt(lastIdAsString);
            lastIdAsInteger++;
            String newId = "S-"+lastIdAsInteger;
            txtStudentID.setText(newId);

        }else {
            txtStudentID.setText("S-1");
        }

    }
}
