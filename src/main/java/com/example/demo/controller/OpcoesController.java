package com.example.demo.controller;

import com.example.demo.Login;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class OpcoesController {

    @FXML
    private Button sairButton;

    @FXML
    private void initialize() {
        sairButton.setOnMouseClicked(event -> {
            try {
                // Voltar para a tela de login (ou qualquer outra tela desejada)
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/view/login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = Login.getStage(); // Obtém a janela principal (Stage)
                stage.setScene(scene); // Muda para a nova cena
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
