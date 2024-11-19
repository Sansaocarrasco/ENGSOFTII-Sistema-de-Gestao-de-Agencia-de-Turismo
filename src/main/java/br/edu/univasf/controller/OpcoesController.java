package br.edu.univasf.controller;

import br.edu.univasf.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class OpcoesController implements Initializable {
    @FXML
    public Button sairButton;
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
            Main.switchScreen("cadastroCliente");
        });
        reservaPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("reservaPacote");
        });
        cadastroPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("cadastroPacote");
        });

        relatorioReservaButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("relatorioReserva");
        });

        sairButton.setOnMouseClicked((MouseEvent event) -> {
            fecha();
        });
    }

    private void fecha() {
        stage.close();
    }
}
