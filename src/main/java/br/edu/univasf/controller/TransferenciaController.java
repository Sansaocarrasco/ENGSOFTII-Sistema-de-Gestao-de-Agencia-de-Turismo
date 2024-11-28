package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.reservaDAO;
import br.edu.univasf.utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TransferenciaController implements Initializable {

    @FXML
    public Button voltarButton;

    @FXML
    private Button confirmarTransferenciaButton; // Botão para confirmar pagamento

    @FXML
    private TextField numeroContaField; // Campo para número da conta

    @FXML
    private TextField agenciaField; // Campo para agência

    @FXML
    private TextField cpfTitularField; // Campo para CPF do titular

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Preenche o CPF automaticamente com o valor da sessão
        preencherCpfTitular();

        // Configura o evento de clique no botão "Confirmar Transferência"
        confirmarTransferenciaButton.setOnMouseClicked((MouseEvent event) -> {
            validarEConfirmarPagamento();
        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("gerenciarPagamento");
        });
    }

    private void preencherCpfTitular() {
        // Verifica se há um cliente na sessão
        if (Session.getCliente() != null) {
            String cpfCliente = Session.getCliente().getCpf(); // Obtém o CPF do cliente da sessão
            cpfTitularField.setText(cpfCliente); // Preenche o campo cpfTitularField
        }
    }

    private void validarEConfirmarPagamento() {
        // Obtém os valores inseridos pelo cliente
        String numeroConta = numeroContaField.getText().replaceAll("\\s", ""); // Remove espaços em branco
        String agencia = agenciaField.getText();
        String cpfTitular = cpfTitularField.getText();

        // Valida se todos os campos foram preenchidos
        if (numeroConta.isEmpty() || agencia.isEmpty() || cpfTitular.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return;
        }

        // Validação do CPF do titular (aqui você pode adicionar uma validação mais robusta se necessário)
        if (!cpfTitular.matches("\\d{11}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("O CPF do titular deve conter exatamente 11 dígitos.");
            alert.showAndWait();
            return;
        }

        // Aqui você pode validar a conta bancária, caso necessário
        if (validarContaBancaria(numeroConta, agencia)) {
            // Se a conta bancária for válida, confirma o pagamento
            confirmarPagamento();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("Número da conta ou agência inválidos!");
            alert.showAndWait();
        }
    }

    // Método para validar a conta bancária
    private boolean validarContaBancaria(String numeroConta, String agencia) {
        // Aqui você pode adicionar a lógica de validação da conta bancária (por exemplo, consulta ao banco de dados)
        // Neste exemplo, vamos considerar que a validação é sempre bem-sucedida
        return true;
    }

    private void confirmarPagamento() {
        try {
            // Obtém o CPF do cliente da sessão
            String cpfCliente = Session.getCliente().getCpf(); // Obtém o CPF do cliente da sessão

            // Atualiza o status de pagamento no banco (você pode alterar este método conforme necessário)
            reservaDAO reservaDAO = new reservaDAO();
            reservaDAO.atualizarStatusPagamentoPorCliente(cpfCliente); // Atualiza o status de pagamento para "true"

            // Exibe uma mensagem de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pagamento Confirmado");
            alert.setHeaderText(null);
            alert.setContentText("Pagamento confirmado com sucesso!");
            alert.showAndWait();

            // Redireciona para a tela de opções
            Main.switchScreen("opcoes");

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao processar o pagamento: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
