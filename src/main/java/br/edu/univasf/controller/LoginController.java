package br.edu.univasf.controller;

import br.edu.univasf.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import java.util.ResourceBundle;

import java.net.URL;

import static br.edu.univasf.Main.stage;

public class LoginController implements Initializable {

    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loginButton.setOnMouseClicked((MouseEvent event) -> {
            if (usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("1234")) {
                // Se login for bem-sucedido, transita para a tela principal
                Main.switchScreen("opcoes");
            }
            else {
                loginMessageLabel.setText("Usuário ou senha Invalidos!");
            }
        });
    }

    private void fecha() {
        stage.close();
    }

}