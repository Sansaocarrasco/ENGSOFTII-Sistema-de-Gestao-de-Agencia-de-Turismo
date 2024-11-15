package com.example.demo.controller;

import com.example.demo.CadastroCliente;
import com.example.demo.CadastroPacote;
import com.example.demo.Opcoes;
import com.example.demo.ReservaPacote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            CadastroCliente cadastroCliente = new CadastroCliente();
            fecha();
            try {
                cadastroCliente.start(new Stage());
            } catch (IOException e) {
                Logger.getLogger(CadastroClienteController.class.getName()).log(Level.SEVERE, null, e);
            }
        });
        reservaPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            ReservaPacote reservaPacote = new ReservaPacote();
            fecha();
            try {
                reservaPacote.start(new Stage());
            } catch (IOException e) {
                Logger.getLogger(ReservaPacoteController.class.getName()).log(Level.SEVERE, null, e);
            }
        });
        cadastroPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            CadastroPacote cadastroPacote = new CadastroPacote();
            fecha();
            try {
                cadastroPacote.start(new Stage());
            } catch (IOException e) {
                Logger.getLogger(CadastroPacoteController.class.getName()).log(Level.SEVERE, null, e);
            }
        });

        relatorioReservaButton.setOnMouseClicked((MouseEvent event) -> {
            // Ta faltando
            fecha();
        });
    }

    private void fecha() {
        stage.close();
    }
}
