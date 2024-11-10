package com.example.demo.controller;

import com.example.demo.Login;
import com.example.demo.Opcoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import java.net.URL;

public class LoginController implements Initializable {

    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private ImageView brandingImageView;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("Images/logo.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        loginButton.setOnMouseClicked((MouseEvent event) -> {
            if (usernameTextField.getText().equals("joaopedro") && passwordTextField.getText().equals("123")) {
                System.out.println("Logado");
                Opcoes opcoes = new Opcoes();

                try {
                    opcoes.start(new Stage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            else {
                loginMessageLabel.setText("Usuário ou senha errados. Tente Novamente!");
            }
        });
    };

    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().equals("joaopedro") && passwordTextField.getText().equals("123")) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}