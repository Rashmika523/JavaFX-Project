package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.Student;
import com.insitute.iimanage.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void initialize(){
        generateTeacherID();
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Dashboard");
    }

    public void saveTeacherOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void generateTeacherID() {

        if (!Database.teacherTable.isEmpty()) {

            Teacher lastTeacher = Database.teacherTable.get(Database.teacherTable.size() - 1);
            String stringId = lastTeacher.getTeacherId();
            String[] split = stringId.split("-");
            String lastIdAsString = split[1];
            int lastIdAsInteger = Integer.parseInt(lastIdAsString);
            lastIdAsInteger++;
            String newId = "T-" + lastIdAsInteger;
            textTeacherID.setText(newId);

        } else {
            textTeacherID.setText("T-1");
        }

    }
}
