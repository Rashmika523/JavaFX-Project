package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.DBConnection;
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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    String searchText = "";

    public void initialize() {

        colStudnetID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        genarateStudentID();
        setTableData(searchText);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setTableDataValue(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });

    }


    public void newStudentOnAction(ActionEvent actionEvent) {

        genarateStudentID();
        setTableData(searchText);
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

            //connect mysql database
            try {
                if (saveStudent(student)) {
                    genarateStudentID();
                    clear();
                    setTableData(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Student has been Saved...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong...!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            Database.studentTable.add(student);

            System.out.println(student.toString());
        } else {

            //connect with mysql database
            Student student = new Student();
            student.setId(txtStudentID.getText());
            student.setAddress(txtAddress.getText());
            student.setName(txtFullName.getText());
            student.setDob(Date.from(txtDob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            try {
                if(updateStudent(student)){
                    setTableData(searchText);
                    clear();
                    genarateStudentID();
                    new Alert(Alert.AlertType.INFORMATION, "Student has been updated...!").show();
                    btnSaveStudnet.setText("Save Student");
                    return;
                }else {
                    new Alert(Alert.AlertType.INFORMATION, "Something went wrong...!").show();
                }
            }catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }

           /* for (Student student : Database.studentTable) {

                if (student.getId().equals(txtStudentID.getText())) {
                    student.setAddress(txtAddress.getText());
                    student.setName(txtFullName.getText());
                    student.setDob(Date.from(txtDob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

                    setTableData(searchText);
                    clear();
                    genarateStudentID();
                    new Alert(Alert.AlertType.INFORMATION, "Student has been updated...!").show();
                    btnSaveStudnet.setText("Save Student");
                    return;
                }
            }*/

        }
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void genarateStudentID() {

       /* if (!Database.studentTable.isEmpty()) {

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
        }*/
        try {
            String stringId = getLastId();
            if (stringId != null) {
                String[] split = stringId.split("-");
                String lastIdAsString = split[1];
                int lastIdAsInteger = Integer.parseInt(lastIdAsString);
                lastIdAsInteger++;
                String newId = "S-" + lastIdAsInteger;
                txtStudentID.setText(newId);
            } else {
                txtStudentID.setText("S-1");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void clear() {
        txtFullName.clear();
        txtDob.setValue(null);
        txtAddress.clear();
    }

    private void setTableData(String name) {

        ObservableList<StudentTm> oblist = FXCollections.observableArrayList();

        //connect with mysql database;

        try {
            List<Student> studentList = searchStudent(name);

            for (Student student:studentList) {
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

                    if (buttonType.get().equals(ButtonType.YES)) {
                       // Database.studentTable.remove(student);
                        try{
                            if(deleteStudent(student.getId())){
                                new Alert(Alert.AlertType.INFORMATION, "Student has Been Deleted...!");
                                setTableData(searchText);
                            }else {
                                new Alert(Alert.AlertType.INFORMATION, "Something went wrong...!");
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }

                    }

                });
            }



        }catch (ClassNotFoundException | SQLException  e){
            e.printStackTrace();
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

    private boolean saveStudent(Student student) throws ClassNotFoundException, SQLException {

       /* Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/iitmanage", "root", "1234");*/
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO student VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, student.getId());
        preparedStatement.setString(2, student.getName());
        preparedStatement.setObject(3, student.getDob());
        preparedStatement.setString(4, student.getAddress());

        return preparedStatement.executeUpdate() > 0;

    }

    private String getLastId() throws ClassNotFoundException, SQLException {
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/iitmanage", "root", "1234");*/
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT student_id FROM student  ORDER BY " +
                "CAST(SUBSTRING(student_id,3) AS UNSIGNED ) DESC LIMIT 1";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }

        return null;
    }

    private List<Student> searchStudent(String text) throws ClassNotFoundException, SQLException {

        text = "%" + text + "%";
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/iitmanage", "root", "1234");*/
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM student WHERE full_name LIKE ? OR address LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, text);
        preparedStatement.setString(2, text);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> list = new ArrayList<>();

        while (resultSet.next()) {

            list.add(new Student(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDate(3),
                            resultSet.getString(4)
                    )
            );
        }

        return list;
    }

    private boolean deleteStudent(String id) throws ClassNotFoundException, SQLException {
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/iitmanage", "root", "1234");*/
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="DELETE FROM student WHERE student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        return preparedStatement.executeUpdate()>0;
    }

    private boolean updateStudent(Student student) throws ClassNotFoundException, SQLException {
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/iitmanage", "root", "1234");*/
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "UPDATE student SET full_name=?,dob=?,address=? WHERE student_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,student.getName());
        preparedStatement.setObject(2,student.getDob());
        preparedStatement.setString(3,student.getAddress());
        preparedStatement.setString(4,student.getId());
         return preparedStatement.executeUpdate()>0;
    }

}
