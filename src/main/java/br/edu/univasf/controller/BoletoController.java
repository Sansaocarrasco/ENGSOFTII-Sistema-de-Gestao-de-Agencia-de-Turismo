package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class BoletoController {

    @FXML
    public Button confirmarBoletoButton;

    @FXML
    public Button voltarButton;

    public double valorTotal;

    @FXML
    public Label valorTotalLabel;

    @FXML
    private TextField numeroBoletoField;

    @FXML
    private TextField cpfBoletoField;

    @FXML
    public void confirmarPagamento(ActionEvent event) {
        try {
            // Obtém o CPF do cliente da sessão
            String cpfCliente = Session.getCliente().getCpf();

            // Atualiza o status do pagamento das reservas do cliente no banco de dados
            br.edu.univasf.dao.reservaDAO reservaDAO = new br.edu.univasf.dao.reservaDAO();
            reservaDAO.atualizarStatusPagamentoPorCliente(cpfCliente);

            // Exibe um alerta de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pagamento Confirmado");
            alert.setHeaderText(null);
            alert.setContentText("Pagamento confirmado com sucesso!");
            alert.showAndWait();

            // Redireciona para a tela de opções
            Main.switchScreen("opcoes");

        } catch (Exception e) {
            e.printStackTrace(); // Exibe o erro no console
            // Exibe uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao atualizar o status do pagamento: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void initialize() {
        // Preenche o campo CPF automaticamente com o CPF da sessão
        cpfBoletoField.setText(Session.getCliente().getCpf());

        // Garante que o método 'confirmarPagamento' será chamado ao clicar no botão
        confirmarBoletoButton.setOnAction(this::confirmarPagamento);

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("gerenciarPagamento");
        });
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        valorTotalLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
    }
}
