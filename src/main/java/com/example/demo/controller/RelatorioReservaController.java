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

import static com.example.demo.RelatorioReserva.stage;

public class RelatorioReservaController implements Initializable {
    @FXML
    public TableView tableRelatorio;
    @FXML
    public Button carregarDadosButton;
    @FXML
    public Button gerarRelatorioButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gerarRelatorioButton.setOnMouseClicked((MouseEvent event) -> {
            // Fazer a função de gerar relatorio da reserva do cliente
            fecha();
        });
    }
    private void fecha() {
        stage.close();
    }
}
