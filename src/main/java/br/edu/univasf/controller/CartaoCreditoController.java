package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CartaoCreditoController implements Initializable {

    @FXML
    private Button confirmarCartaoButton;  // Botão para confirmar o pagamento

    @FXML
    private Label valorTotalLabel;
    @FXML
    private Label valorParcelaLabel;

    private double valorTotal;
    private double valorParcela;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura o evento de clique no botão "Confirmar Cartão"
        confirmarCartaoButton.setOnMouseClicked((MouseEvent event) -> {
            confirmarPagamento();
        });
    }

    // Método para receber o valor total das reservas
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        valorTotalLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
    }

    // Método para receber o valor da parcela
    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
        valorParcelaLabel.setText("Valor por Parcela: R$ " + String.format("%.2f", valorParcela));
    }

    private void confirmarPagamento() {
        // Aqui não precisa verificar a reserva, pois você só quer mostrar um alerta e redirecionar
        // Exibe um alerta de sucesso
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pagamento Confirmado");
        alert.setHeaderText(null);
        alert.setContentText("Pagamento confirmado com sucesso!");
        alert.showAndWait();

        // Redireciona para a tela de opções
        Main.switchScreen("opcoes");
    }
}
