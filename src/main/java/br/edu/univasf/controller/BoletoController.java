package br.edu.univasf.controller;

import br.edu.univasf.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class BoletoController {

    @FXML
    public Button confirmarBoletoButton;

    @FXML
    private TextField numeroBoletoField;

    @FXML
    private TextField cpfBoletoField;

    @FXML
    public void confirmarPagamento(ActionEvent event) {
        // Aqui, vamos exibir o alerta de pagamento confirmado

        // Exibe um alerta de sucesso
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pagamento Confirmado");
        alert.setHeaderText(null);
        alert.setContentText("Pagamento confirmado com sucesso!");
        alert.showAndWait();

        // Redireciona para a tela de opções
        Main.switchScreen("opcoes");
    }

    @FXML
    public void initialize() {
        // Garante que o método 'confirmarPagamento' será chamado ao clicar no botão
        confirmarBoletoButton.setOnAction(this::confirmarPagamento);
    }
}
