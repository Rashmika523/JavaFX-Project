package com.insitute.iimanage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CodeVerificationForm {
    public AnchorPane context;
    public Label lblEmail;
    public TextField txtCode;

    int code=0;
    String selectEmail="";
    public void changeEmailOnAction(ActionEvent actionEvent) {
    }

    public void verifyCodeOnAction(ActionEvent actionEvent) throws IOException {

        if(String.valueOf(code).equals(txtCode.getText())){
            //navigate rest password
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RestPasswordForm.fxml"));
            Parent parent = fxmlLoader.load();
            RestPasswordFormController controller = fxmlLoader.getController();
            controller.setUserData(selectEmail);
            Stage stage =(Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));

        }else {
            new Alert(Alert.AlertType.ERROR,"Wrong Verification Code,Try again!").show();
        }

    }

    public void setUserData(int code,String email){
        selectEmail=email;
        lblEmail.setText(email);
        this.code=code;
        System.out.println(email);
        System.out.println(code);
    }
}
