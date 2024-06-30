package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.Student;
import com.insitute.iimanage.model.Tm.StudentTm;
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

public class StudentFormController {
    public AnchorPane context;
    public TextField txtStudentID;
    public TextField txtFullName;
    public DatePicker txtDob;
    public TextField txtAddress;
    public Button btnSaveStudnet;
    public TextField txtSearch;

    public TableView<StudentTm> tblStudent;
    public TableColumn<StudentTm, String> colStudnetID;
    public TableColumn<StudentTm, String> colFullName;
    public TableColumn<StudentTm, String> colDob;
    public TableColumn<StudentTm, String> colAddress;
    public TableColumn<StudentTm, Button> colOption;

    public void initialize() {

        colStudnetID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        genarateStudentID();
        setTableData();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setTableDataValue(newValue);
            }
        });

    }


    public void newStudentOnAction(ActionEvent actionEvent) {

        genarateStudentID();
        setTableData();
        clear();
        btnSaveStudnet.setText("Save Student");

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Dashboard");
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {

        if (btnSaveStudnet.getText().equalsIgnoreCase("Save Student")) {
            Student student = new Student(
                    txtStudentID.getText(),
                    txtFullName.getText(),
                    Date.from(txtDob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    txtAddress.getText()
            );

            Database.studentTable.add(student);
            genarateStudentID();
            clear();
            setTableData();
            new Alert(Alert.AlertType.INFORMATION, "Student has been Saved...!").show();
            System.out.println(student.toString());
        } else {

            for (Student student : Database.studentTable) {

                if (student.getId().equals(txtStudentID.getText())) {
                    student.setAddress(txtAddress.getText());
                    student.setName(txtFullName.getText());
                    student.setDob(Date.from(txtDob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

                    setTableData();
                    clear();
                    genarateStudentID();
                    new Alert(Alert.AlertType.INFORMATION, "Student has been updated...!").show();
                    btnSaveStudnet.setText("Save Student");
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

    private void genarateStudentID() {

        if (!Database.studentTable.isEmpty()) {

            Student lastStudent = Database.studentTable.get(Database.studentTable.size() - 1);
            String stringId = lastStudent.getId();
            String[] split = stringId.split("-");
            String lastIdAsString = split[1];
            int lastIdAsInteger = Integer.parseInt(lastIdAsString);
            lastIdAsInteger++;
            String newId = "S-" + lastIdAsInteger;
            txtStudentID.setText(newId);

        } else {
            txtStudentID.setText("S-1");
        }

    }

    private void clear() {
        txtFullName.clear();
        txtDob.setValue(null);
        txtAddress.clear();
    }

    private void setTableData() {

        ObservableList<StudentTm> oblist = FXCollections.observableArrayList();

        for (Student student : Database.studentTable) {

            Button button = new Button("Delete");

            oblist.add(new StudentTm(
                    student.getId(),
                    student.getName(),
                    new SimpleDateFormat("yyyy-MM-dd").format(student.getDob()),
                    student.getAddress(),
                    button
            ));

            button.setOnAction(event -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure...?", ButtonType.NO, ButtonType.YES);
                Optional<ButtonType> buttonType = alert.showAndWait();

                if(buttonType.get().equals(ButtonType.YES)){
                    Database.studentTable.remove(student);
                    new Alert(Alert.AlertType.INFORMATION,"Student has Been Deleted...!");
                    setTableData();
                }

            });
        }
        tblStudent.setItems(oblist);

    }

    private void setTableDataValue(StudentTm studentTm) {
        txtStudentID.setText(studentTm.getId());
        txtFullName.setText(studentTm.getName());
        txtAddress.setText(studentTm.getAddress());
        txtDob.setValue(LocalDate.parse(studentTm.getDob()));
        btnSaveStudnet.setText("Update Student");
    }
}
