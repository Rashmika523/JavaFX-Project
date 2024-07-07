package com.insitute.iimanage.controller;

import com.insitute.iimanage.db.Database;
import com.insitute.iimanage.model.User;
import com.insitute.iimanage.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class RestPasswordFormController {
    public AnchorPane context;
    public PasswordField txtPassword;

    String selectedEmail = "";

    public void resetOnAction(ActionEvent actionEvent) throws IOException {

        Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(selectedEmail)).
                findFirst();
        if(selectedUser.isPresent()){
            selectedUser.get().setPassword(new PasswordManager().encrypt(txtPassword.getText().trim()));
            new Alert(Alert.AlertType.INFORMATION,"Your Password has been updated!").show();
            setUI("LoginForm");
        }else {
            new Alert(Alert.AlertType.ERROR,"User Not Found!").show();
            setUI("LoginForm");
        }

    }

    public void setUserData(String email) {
        selectedEmail = email;
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
