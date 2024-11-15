package com.example.demo.controller;

import com.example.demo.Login;
import com.example.demo.Opcoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
                    // Carregar a tela de login novamente ou qualquer outra tela
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/login.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = Login.getStage(); // Obtém a janela principal (Stage)
                    stage.setScene(scene); // Muda para a nova cena
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else {
                loginMessageLabel.setText("Usuário ou senha errados. Tente Novamente!");
            }
        });
    };

    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().equals("joaopedro") && passwordTextField.getText().equals("123")) {
            System.out.println("Logado");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}