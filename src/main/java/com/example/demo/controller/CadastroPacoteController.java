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

import static com.example.demo.CadastroPacote.stage;

public class CadastroPacoteController implements Initializable {

    @FXML
    private Button cadastrarPacoteButton;

    @FXML
    private TextField nomePacoteTextField;

    @FXML
    private TextField destinoTextField;

    @FXML
    private DatePicker dataInicioPicker;

    @FXML
    private DatePicker dataFimPicker;

    @FXML
    private TextField precoTextField;

    @FXML
    private TextField vagasTextField;

    @FXML
    private CheckBox transporteCheckBox;

    @FXML
    private TextField hospedagemTextField;

    @FXML
    private TextArea atividadesTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrarPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            // Fazer a função de enviar as credenciais do pacote para o banco de dados.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificação");
            alert.setHeaderText(null);
            alert.setContentText("O Pacote foi cadastrado com sucesso na base de dados do Travel Manager!");
            alert.show();
            fecha();
        });
    }
    private void fecha() {
        stage.close();
    }
}
