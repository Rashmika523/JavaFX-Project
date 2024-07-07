package com.insitute.iimanage.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CodeVerificationForm {
    public AnchorPane context;
    public Label lblEmail;
    public TextField txtCode;

    int code=0;

    public void changeEmailOnAction(ActionEvent actionEvent) {
    }

    public void verifyCodeOnAction(ActionEvent actionEvent) {

        if(String.valueOf(code).equals(txtCode.getText())){
            //navigate rest password
        }else {
            new Alert(Alert.AlertType.ERROR,"Wrong Verification Code,Try again!").show();
        }

    }

    public void setUserData(int code,String email){
        lblEmail.setText(email);
        this.code=code;
    }
}
