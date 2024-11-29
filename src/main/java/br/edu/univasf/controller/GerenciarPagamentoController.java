package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.reservaDAO;
import br.edu.univasf.model.Cliente;
import br.edu.univasf.model.Pacote;
import br.edu.univasf.model.Reserva;
import br.edu.univasf.utils.Session;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GerenciarPagamentoController implements Initializable {

    @FXML
    private Label clienteNomeLabel;
    @FXML
    private Label clienteCpfLabel;
    @FXML
    private TableView<Reserva> pacotesTableView;
    @FXML
    private TableColumn<Reserva, String> nomePacoteColumn;
    @FXML
    private TableColumn<Reserva, String> destinoPacoteColumn;
    @FXML
    private TableColumn<Reserva, Double> valorPacoteColumn;
    @FXML
    private ComboBox<String> metodoPagamentoComboBox;
    @FXML
    private Button confirmarPagamentoButton;
    @FXML
    private Button voltarButton;

    private double valorTotal = 0.0;  // Variável temporária para armazenar o valor total

    @FXML
    private ComboBox<Integer> parcelasComboBox;  // ComboBox para selecionar o número de parcelas

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obtém o cliente da sessão
        Cliente cliente = Session.getCliente();

        if (cliente != null) {
            clienteNomeLabel.setText("Nome: " + cliente.getNome());
            clienteCpfLabel.setText("CPF: " + cliente.getCpf());

            reservaDAO reservaDAO = new reservaDAO();
            try {
                // Busca as reservas do cliente pelo CPF
                List<Reserva> reservas = reservaDAO.buscarReservasPorCpf(cliente.getCpf());

                // Verifica se encontrou reservas
                if (reservas.isEmpty()) {
                    // Exibe uma mensagem se não encontrar reservas
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Não há reservas para este cliente.");
                    alert.showAndWait();
                } else {
                    // Adiciona as reservas na TableView
                    pacotesTableView.setItems(FXCollections.observableArrayList(reservas));

                    // Configura as colunas da TableView para exibir os dados corretamente
                    nomePacoteColumn.setCellValueFactory(cellData -> {
                        Pacote pacote = cellData.getValue().getPacote();
                        return new SimpleStringProperty(pacote != null ? pacote.getNome() : "Pacote não disponível");
                    });

                    destinoPacoteColumn.setCellValueFactory(cellData -> {
                        Pacote pacote = cellData.getValue().getPacote();
                        return new SimpleStringProperty(pacote != null ? pacote.getDestino() : "Destino não disponível");
                    });

                    valorPacoteColumn.setCellValueFactory(cellData -> {
                        Pacote pacote = cellData.getValue().getPacote();
                        return new SimpleDoubleProperty(pacote != null ? pacote.getPreco() : 0.0).asObject();
                    });

                    // Soma os valores dos pacotes
                    for (Reserva reserva : reservas) {
                        Pacote pacote = reserva.getPacote();
                        if (pacote != null) {
                            valorTotal += pacote.getPreco();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Exibe um erro em caso de falha na busca
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Erro ao carregar as reservas.");
                alert.showAndWait();
            }
        } else {
            clienteNomeLabel.setText("Cliente não encontrado");
            clienteCpfLabel.setText("");
        }

        // Define as opções do ComboBox
        metodoPagamentoComboBox.setItems(FXCollections.observableArrayList("Cartão de Crédito", "Boleto", "Transferência Bancária"));

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });

        confirmarPagamentoButton.setOnMouseClicked((MouseEvent event) -> {
            // Lógica para navegar para a tela de pagamento específica
            String metodoPagamento = metodoPagamentoComboBox.getValue();

            if (metodoPagamento == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor, selecione um método de pagamento!");
                alert.showAndWait();
            } else {
                try {
                    // Carrega a tela de pagamento com base no método selecionado
                    FXMLLoader loader = null;
                    Parent root = null;

                    switch (metodoPagamento) {
                        case "Cartão de Crédito":
                            Session.setCliente(cliente);
                            loader = new FXMLLoader(getClass().getResource("/br/edu/univasf/view/pagamentoCartao.fxml"));
                            root = loader.load();
                            CartaoCreditoController cartaoCreditoController = loader.getController();
                            cartaoCreditoController.setValorTotal(valorTotal);  // Passa o valor total para o controlador
                            break;
                        case "Boleto":
                            Session.setCliente(cliente);
                            loader = new FXMLLoader(getClass().getResource("/br/edu/univasf/view/pagamentoBoleto.fxml"));
                            root = loader.load();
                            BoletoController boletoController = loader.getController();
                            boletoController.setValorTotal(valorTotal);
                            break;
                        case "Transferência Bancária":
                            Session.setCliente(cliente);
                            loader = new FXMLLoader(getClass().getResource("/br/edu/univasf/view/pagamentoTransferencia.fxml"));
                            root = loader.load();
                            TransferenciaController transferenciaController = loader.getController();
                            transferenciaController.setValorTotal(valorTotal);
                            break;
                    }

                    // Altera a cena para a tela de pagamento correspondente
                    if (root != null) {
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) confirmarPagamentoButton.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar a tela de pagamento.");
                        alert.showAndWait();
                    }

                } catch (IOException e) {
                    e.printStackTrace(); // Exibe o erro no console para mais detalhes
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar a tela FXML: " + e.getMessage());
                    alert.showAndWait();
                }
            }
        });

    }
}
