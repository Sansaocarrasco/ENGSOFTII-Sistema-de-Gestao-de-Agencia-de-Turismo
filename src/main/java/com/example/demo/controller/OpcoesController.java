package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.Opcoes.stage;

public class OpcoesController implements Initializable {

    @FXML
    private Button cadastroClienteButton;
    @FXML
    private Button reservaPacoteButton;
    @FXML
    private Button cadastroPacoteButton;
    @FXML
    private Button relatorioReservaButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cadastroClienteButton.setOnMouseClicked((MouseEvent event) -> {
                fecha();
        });
        reservaPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            fecha();
        });
        cadastroPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            fecha();
        });

        relatorioReservaButton.setOnMouseClicked((MouseEvent event) -> {
            fecha();
        });
    }

    private void fecha() {
        stage.close();
    }
}
