package com.example.demo.controller;

import com.example.demo.CadastroCliente;
import com.example.demo.Opcoes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demo.CadastroCliente.stage;

public class CadastroClienteController implements Initializable {

    @FXML
    private Button cadastrarClienteButton;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cpfTextField;

    @FXML
    private DatePicker dataNascimentoPicker;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private TextArea enderecoTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrarClienteButton.setOnMouseClicked((MouseEvent event) -> {
            // Fazer a função de enviar as credenciais do cliente para o banco de dados.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificação");
            alert.setHeaderText(null);
            alert.setContentText("O Cliente foi cadastrado com sucesso na base de dados do Travel Manager!");
            alert.show();
            fecha();
        });
    }
    private void fecha() {
        stage.close();
    }
}
