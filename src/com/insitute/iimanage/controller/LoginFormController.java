package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.DBConnection;
import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.User;
import com.insitute.iimanage.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public Hyperlink txtForgotPassword;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        String email = txtEmail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();


        //connect login method with mysql database

        try {
            User user = login(email);
            System.out.println(user);
            if (null != user) {
                if (new PasswordManager().checkPassword(password, user.getPassword())) {
                    setUI("Dashboard");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Email or Password Incorrect...!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Found...!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        /*for(User user: Database.userTable){
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    System.out.println(user.toString());
                    return;
                }else {
                    new Alert(Alert.AlertType.ERROR,"Email or Password Incorrect").show();
                    return;
                }
            }
        }
        new Alert(Alert.AlertType.ERROR,"User Not Found...!");*/

       /* Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();
        if (selectedUser.isPresent()) {
            if (new PasswordManager().checkPassword(password, selectedUser.get().getPassword())) {
                setUI("Dashboard");
            } else {
                new Alert(Alert.AlertType.ERROR, "Email or Password Incorrect...!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User Not Found...!").show();
        }*/

    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ForgotPasswordForm");
    }

    private User login(String email) throws ClassNotFoundException, SQLException {

        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/iitmanage", "root", "1234");*/
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM  user WHERE email =?";

        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            return user;
        }
        return null;
    }
}
