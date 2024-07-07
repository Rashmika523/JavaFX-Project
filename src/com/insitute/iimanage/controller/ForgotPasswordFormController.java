package com.insitute.iimanage.controller;

import com.insitute.iimanage.util.tools.VerificationCodeGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class ForgotPasswordFormController {
    public AnchorPane context;
    public TextField txtEmail;

    public void loginOnAction(ActionEvent actionEvent) {
    }

    public void sendVerificationOnAction(ActionEvent actionEvent) {
        int verificationCode =new VerificationCodeGenerator().getCode(5);
        try {
            String fromEmail = "codemaster523523@gmail.com";
            String toEmail = txtEmail.getText(); // replace with actual recipient email

            String host = "10.101.3.229";
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);

            Session session = Session.getDefaultInstance(properties);

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.setSubject("Verification Code");
            mimeMessage.setText("Your Verification Code is: " + verificationCode);
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            //Transport.send(mimeMessage);


            //System.out.println(verificationCode);


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CodeVerificationForm.fxml"));
            Parent parent = fxmlLoader.load();
            CodeVerificationForm controller = fxmlLoader.getController();
            controller.setUserData(verificationCode, txtEmail.getText());
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));


        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
