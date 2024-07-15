package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.Course;
import com.insitute.iimanage.model.Teacher;
import com.insitute.iimanage.model.Tm.CourseTm;
import com.insitute.iimanage.model.Tm.TechnolgiesTm;
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
import java.util.ArrayList;

public class CourseFormController {
    public AnchorPane context;
    public TextField txtStudentID;
    public TextField txtCourseName;
    public TextField txtCost;
    public ComboBox<String> cmbTeachers;
    public TextField txtTechnologies;

    public TableView<TechnolgiesTm> tblTechnologies;
    public TableColumn<TechnolgiesTm, Integer> colTechId;
    public TableColumn<TechnolgiesTm, String> coltech;
    public TableColumn<TechnolgiesTm, Button> colRemove;
    public Button btnSave;
    public TextField txtSearch;

    public TableView<CourseTm> tblCourse;
    public TableColumn<CourseTm, String> colCourseId;
    public TableColumn<CourseTm, String> colCourse;
    public TableColumn<CourseTm, Button> colTechOnCourse;
    public TableColumn<CourseTm, String> colTeacher;
    public TableColumn<CourseTm, Double> colCost;
    public TableColumn<CourseTm, Button> colOption;
    public TextField txtCourseID;


    ArrayList<String> teachersArray = new ArrayList<>();
    ObservableList<TechnolgiesTm> tmList = FXCollections.observableArrayList();

    public void initialize() {
        generateCourseID();
        setTeachers();
        loadCourse();

        colTechId.setCellValueFactory(new PropertyValueFactory<>("code"));
        coltech.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("button"));

        colCourseId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTechId.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        colTechOnCourse.setCellValueFactory(new PropertyValueFactory<>("btnTechList"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnOption"));

    }

    public void newCourseOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("Dashboard");
    }

    public void addTechOnAction(ActionEvent actionEvent) {

        if (!isExist(txtTechnologies.getText().trim())) {
            Button button = new Button("Remove");

            tmList.add(
                    new TechnolgiesTm(
                            tmList.size() + 1,
                            txtTechnologies.getText().trim(),
                            button
                    )
            );
            tblTechnologies.setItems(tmList);
            txtTechnologies.clear();
        } else {
            txtTechnologies.selectAll();
            new Alert(Alert.AlertType.INFORMATION, "This tech is already exists").show();
        }

    }

    public void saveCourseOnAction(ActionEvent actionEvent) {

        String[] selectedTech = new String[tmList.size()];
        int pointer = 0;

        for (TechnolgiesTm t : tmList) {
            selectedTech[pointer] = t.getName();
            pointer++;
        }

        if (btnSave.getText().equalsIgnoreCase("Save Course")) {
            Course course = new Course(
                    txtCourseID.getText(),
                    txtCourseName.getText(),
                    selectedTech,
                    cmbTeachers.getValue().split("\\.")[0],
                    Double.parseDouble(txtCost.getText().trim())
            );
            Database.courseTable.add(course);
            new Alert(Alert.AlertType.INFORMATION,"Course has been saved..!").show();
            generateCourseID();
            loadCourse();
        }
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void generateCourseID() {

        if (!Database.courseTable.isEmpty()) {

            Course lastCourse = Database.courseTable.get(Database.courseTable.size() - 1);
            String stringId = lastCourse.getCourseId();
            String[] split = stringId.split("-");
            String lastIdAsString = split[1];
            int lastIdAsInteger = Integer.parseInt(lastIdAsString);
            lastIdAsInteger++;
            String newId = "C-" + lastIdAsInteger;
            txtCourseID.setText(newId);

        } else {
            txtCourseID.setText("C-1");
        }

    }

    private void setTeachers() {

        for (Teacher t : Database.teacherTable) {
            teachersArray.add(t.getTeacherId() + ". " + t.getName());
        }

        ObservableList<String> oblist = FXCollections.observableArrayList(teachersArray);
        cmbTeachers.setItems(oblist);

    }

    private boolean isExist(String tech) {
        for (TechnolgiesTm tm : tmList) {
            if (tm.getName().equalsIgnoreCase(tech)) {
                return true;
            }
        }
        return false;
    }

    private void loadCourse(){
        ObservableList<CourseTm> courseList = FXCollections.observableArrayList();

        for (Course c : Database.courseTable){
            Button  techButton = new Button("Show Tech");
            Button deleteButton = new Button("Delete");

            CourseTm  tm = new CourseTm(
                    c.getCourseId(),
                    c.getCourseName(),
                    c.getTeacherId(),
                    techButton,
                    c.getCost(),
                    deleteButton
            );

            courseList.add(tm);
        }
        tblCourse.setItems(courseList);
    }
}
