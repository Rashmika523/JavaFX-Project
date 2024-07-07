package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.Student;
import com.insitute.iimanage.model.Teacher;
import com.insitute.iimanage.model.Tm.StudentTm;
import com.insitute.iimanage.model.Tm.TeacherTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class TeacherFormController {
    public AnchorPane context;
    public TextField textTeacherID;
    public TextField txtFullName;
    public TextField txtAddress;
    public Button btnSaveTeacher;
    public TextField txtSearch;
    public TableView<TeacherTm> tblTeacher;
    public TableColumn<TeacherTm,String> colTeacherId;
    public TableColumn<TeacherTm,String> colFullName;
    public TableColumn<TeacherTm,String> colAddress;
    public TableColumn<TeacherTm,String> colContact;
    public TableColumn<TeacherTm,Button> colOption;
    public TextField txtContact;

    private String searchText="";

    public void initialize() {

        colTeacherId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        generateTeacherID();
        setTableData(searchText);

        tblTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setTableDataValue(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });
    }

    public void newTeacherOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Dashboard");
    }

    public void saveTeacherOnAction(ActionEvent actionEvent) {

        if (btnSaveTeacher.getText().equalsIgnoreCase("Save Teacher")) {
            Teacher teacher = new Teacher(
                    textTeacherID.getText(),
                    txtFullName.getText(),
                    txtAddress.getText(),
                    txtContact.getText()
            );

            Database.teacherTable.add(teacher);
            generateTeacherID();
            clear();
            setTableData(searchText);
            new Alert(Alert.AlertType.INFORMATION, "Teacher has been Saved...!").show();
        } else {

            for (Teacher teacher : Database.teacherTable) {

                if (teacher.getTeacherId().equals(textTeacherID.getText())) {
                    teacher.setAddress(txtAddress.getText());
                    teacher.setName(txtFullName.getText());
                    teacher.setContact(txtContact.getText());

                    setTableData(searchText);
                    clear();
                    generateTeacherID();
                    new Alert(Alert.AlertType.INFORMATION, "Teacher has been updated...!").show();
                    btnSaveTeacher.setText("Save Teacher");
                    return;
                }
            }

        }

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
    private void clear(){
        txtContact.clear();
        txtAddress.clear();
        txtFullName.clear();
    }

    private void setTableData(String name) {

        ObservableList<TeacherTm> oblist = FXCollections.observableArrayList();

        for (Teacher teacher : Database.teacherTable) {

            if(teacher.getName().contains(name)){

                Button button = new Button("Delete");

                oblist.add(new TeacherTm(
                        teacher.getTeacherId(),
                        teacher.getName(),
                        teacher.getAddress(),
                        teacher.getContact(),
                        button
                ));

                button.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure...?", ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if(buttonType.get().equals(ButtonType.YES)){
                        Database.teacherTable.remove(teacher);
                        new Alert(Alert.AlertType.INFORMATION,"Teacher has Been Deleted...!");
                        setTableData(searchText);
                    }

                });
            }
        }
        tblTeacher.setItems(oblist);

    }

    private void setTableDataValue(TeacherTm teacherTm) {
        textTeacherID.setText(teacherTm.getId());
        txtFullName.setText(teacherTm.getName());
        txtAddress.setText(teacherTm.getAddress());
        txtContact.setText(teacherTm.getContact());
        btnSaveTeacher.setText("Update Teacher");
    }

}
