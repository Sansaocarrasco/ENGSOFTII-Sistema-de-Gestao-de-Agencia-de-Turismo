package br.edu.univasf.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

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
