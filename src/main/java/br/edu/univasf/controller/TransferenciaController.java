package br.edu.univasf.controller;

import br.edu.univasf.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class TransferenciaController {

    @FXML
    public Button confirmarTransferenciaButton;

    @FXML
    private TextField numeroContaField;

    @FXML
    private TextField agenciaField;

    @FXML
    private TextField cpfTitularField;

    @FXML
    public void confirmarPagamento(ActionEvent event) {
        // Exibe um alerta de pagamento confirmado

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
        // Configura o evento de clique para o botão confirmarTransferenciaButton
        confirmarTransferenciaButton.setOnAction(this::confirmarPagamento);
    }
}
