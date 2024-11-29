package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.pacoteDAO;
import br.edu.univasf.model.Relatorio;
import br.edu.univasf.utils.ConnectionFactory;
import br.edu.univasf.utils.Validators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.*;

import static br.edu.univasf.Main.stage;

public class GerarItinerarioController implements Initializable {

    @FXML
    private TextField cpfClienteTextField;
    @FXML
    private TextField nomePacoteTextField;
    @FXML
    public TableView<Relatorio> tableRelatorio;
    @FXML
    private TableColumn<Relatorio, String> colNomeCliente;
    @FXML
    private TableColumn<Relatorio, String> colItinerario;
    @FXML
    private TableColumn<Relatorio, String> colPacote;
    @FXML
    private TableColumn<Relatorio, Double> colPreco;
    @FXML
    private TableColumn<Relatorio, String> colDataReserva;
    @FXML
    private TableColumn<Relatorio, String> colPagamento;
    @FXML
    private TableColumn<Relatorio, String> colTransporte;
    @FXML
    public Button gerarItinerarioButton;
    @FXML
    public Button voltarButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colItinerario.setCellValueFactory(new PropertyValueFactory<>("itinerario"));
        colPacote.setCellValueFactory(new PropertyValueFactory<>("nomePacote"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("precoReserva"));
        colDataReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));
        colPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
        colTransporte.setCellValueFactory(new PropertyValueFactory<>("transporte"));

        gerarItinerarioButton.setOnMouseClicked((MouseEvent event) -> {

            String cpfCliente = cpfClienteTextField.getText(); // Remove espaços extras
            String nomePacoteQuery = nomePacoteTextField.getText();

            // Verifica se os campos estão preenchidos antes de continuar
            if (cpfCliente.isEmpty() || nomePacoteQuery.isEmpty()) {
                Validators.mostrarAlerta("Erro", "Campos obrigatórios vazios", "Preenchar todos os campos necessários", false);
                return; // Interrompe a execução se os campos estiverem vazios
            }

//            if(Validators.isValidCPF(cpfCliente))
//            {
//                Validators.mostrarAlerta("Erro", "CPF inválido", "Forneça um número de cpf válido", false);
//                return; // Interrompe a execução se os campos estiverem vazios
//            }

            // Chama a função para buscar o ID do pacote
            int pacoteID = pacoteDAO.buscarIDPacote(nomePacoteQuery);

            // Verifica se um ID válido foi encontrado
            if (pacoteID == 0) {
                Validators.mostrarAlerta("Erro", "Pacote não encontrado", "Não há pacotes com esse nome ou o cliente não fez reserva", false);
                return;
            }

            ObservableList<Relatorio> relatorio = FXCollections.observableArrayList();

            String query = "SELECT cliente.nome AS cliente, pacote.nome AS pacote, itinerario, transporte, preco, datareserva, statuspagamento " +
                    "FROM cliente, reserva, pacote " +
                    "WHERE cpfcliente = ? AND cpf = ? AND pkfkpacoteid = ? AND pacoteid = '1'";

            try (Connection conn = new ConnectionFactory().getConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(query)) {

                // Substitui os parâmetros na query
                preparedStatement.setString(1, cpfCliente);
                preparedStatement.setString(2, cpfCliente);
                preparedStatement.setInt(3, pacoteID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.isBeforeFirst()) {
                        Validators.mostrarAlerta("Erro", "Nenhum dado encontrado", "Não há registros para os critérios informados.", false);
                        return;
                    }

                    while (resultSet.next()) {
                        String nomeCliente = resultSet.getString("cliente");
                        String itinerario = resultSet.getString("itinerario");
                        String nomePacote = resultSet.getString("pacote");
                        Double preco = resultSet.getDouble("preco");
                        String data = resultSet.getString("datareserva");
                        boolean pagamento = resultSet.getBoolean("statuspagamento");
                        boolean transporte = resultSet.getBoolean("transporte");

                        relatorio.add(new Relatorio(nomeCliente, nomePacote, data, preco, pagamento, transporte, itinerario));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            tableRelatorio.setItems(relatorio);

        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });

    }
    private void fecha() {
        stage.close();
    }
}
