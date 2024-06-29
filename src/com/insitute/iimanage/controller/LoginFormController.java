package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.User;
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
import java.util.Optional;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public Hyperlink txtForgotPassword;

    public void loginOnAction(ActionEvent actionEvent) {

        String email = txtEmail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();

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

        Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();
        if(selectedUser.isPresent()){
            if (selectedUser.get().getPassword().equals(password)){
                System.out.println(selectedUser.get());
            }else {
                new Alert(Alert.AlertType.ERROR,"Email or Password Incorrect...!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"User Not Found...!").show();
        }

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
}
