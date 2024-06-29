package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.User;
import com.insitute.iimanage.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInput;
import java.io.IOException;
import java.util.Locale;

public class SignupFormController {

    public AnchorPane context;
    public TextField txtFirstName;
    public PasswordField txtPassword;
    public TextField txtLastName;
    public TextField txtEmail;

    public void singupOnAction(ActionEvent actionEvent) throws IOException {

        String firstName = txtFirstName.getText().trim().toLowerCase();
        String lastname = txtLastName.getText().trim().toLowerCase();
        String email = txtEmail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();

        Database.userTable.add(
                new User(firstName,lastname,email,new PasswordManager().encrypt(password))
        );
        new Alert(Alert.AlertType.CONFIRMATION,"Your Account has been Created...!").show();
        setUI("LoginForm");

    }

    public void alreadyHaveanAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
