package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.reservaDAO;
import br.edu.univasf.utils.Session;
import br.edu.univasf.utils.Validators;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CartaoCreditoController implements Initializable {

    @FXML
    private Button voltarButton;
    @FXML
    private Button confirmarCartaoButton; // Botão para confirmar o pagamento
    @FXML
    private TextField numeroCartaoField; // Campo para número do cartão
    @FXML
    private TextField nomeTitularField;  // Campo para nome do titular
    @FXML
    private TextField validadeField;     // Campo para validade (MM/AAAA)
    @FXML
    private TextField cvvField;          // Campo para CVV
    @FXML
    private ComboBox<Integer> parcelasComboBox; // ComboBox para selecionar número de parcelas
    @FXML
    private Label valorTotalLabel;       // Exibe o valor total

    private double valorTotal; // Valor total da compra

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura o evento de clique no botão "Confirmar Cartão"
        confirmarCartaoButton.setOnMouseClicked((MouseEvent event) -> {
            validarEConfirmarPagamento();
        });
        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("gerenciarPagamento");
        });
    }

    // Método para receber o valor total das reservas
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        valorTotalLabel.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
    }


    private void validarEConfirmarPagamento() {
        // Obtém os valores inseridos pelo cliente
        String numeroCartao = numeroCartaoField.getText().replaceAll("\\s", ""); // Remove todos os espaços
        String nomeTitular = nomeTitularField.getText();
        String validade = validadeField.getText();
        String cvv = cvvField.getText();
        Integer parcelas = parcelasComboBox.getValue();

        // Valida se todos os campos foram preenchidos
        if (numeroCartao.isEmpty() || nomeTitular.isEmpty() || validade.isEmpty() || cvv.isEmpty() || parcelas == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return;
        }

        // Valida se o número do cartão contém exatamente 16 dígitos após remover os espaços
        if (!numeroCartao.matches("\\d{16}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("O número do cartão deve conter exatamente 16 dígitos, sem caracteres inválidos.");
            alert.showAndWait();
            return;
        }

        // Validação básica do formato da validade (MM/AAAA)
        if (!validade.matches("\\d{2}/\\d{4}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("A validade deve estar no formato MM/AAAA.");
            alert.showAndWait();
            return;
        }

        // Separa o mês e o ano de validade
        String[] validadeSplit = validade.split("/");
        int mesValidade = Integer.parseInt(validadeSplit[0]);
        int anoValidade = Integer.parseInt(validadeSplit[1]);

        // Valida o cartão de crédito
        if (Validators.validarCartao(numeroCartao, mesValidade, anoValidade, cvv, "N/A")) {
            // Se válido, confirma o pagamento
            confirmarPagamento(parcelas);
        } else {
            // Exibe uma mensagem de erro se o cartão for inválido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("Os dados do cartão de crédito são inválidos. Verifique as informações.");
            alert.showAndWait();
        }
    }

    private void confirmarPagamento(Integer parcelas) {
        try {
            // Obtém o cliente da sessão
            String cpfCliente = Session.getCliente().getCpf();

            // Atualiza o status do pagamento no banco
            reservaDAO reservaDAO = new reservaDAO();
            reservaDAO.atualizarStatusPagamentoPorCliente(cpfCliente);

            // Exibe mensagem de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pagamento Confirmado");
            alert.setHeaderText(null);
            alert.setContentText("Pagamento confirmado com sucesso em " + parcelas + " parcela(s)!");
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
