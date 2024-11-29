package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.model.Relatorio;
import br.edu.univasf.utils.ConnectionFactory;
import javafx.beans.Observable;
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

import static br.edu.univasf.Main.main;
import static br.edu.univasf.Main.stage;

public class RelatorioReservaController implements Initializable {
    @FXML
    public TableView<Relatorio> tableRelatorio;
    @FXML
    private TableColumn<Relatorio, String> colNomeCliente;
    @FXML
    private TableColumn<Relatorio, String> colCpf;
    @FXML
    private TableColumn<Relatorio, String> colPacote;
    @FXML
    private TableColumn<Relatorio, Double> colPreco;
    @FXML
    private TableColumn<Relatorio, String> colDataReserva;
    @FXML
    private TableColumn<Relatorio, String> colPagamento;
    @FXML
    public Button gerarItinerarioButton;
    @FXML
    public Button gerarRelatorioButton;
    @FXML
    public Button voltarButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colPacote.setCellValueFactory(new PropertyValueFactory<>("nomePacote"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("precoReserva"));
        colDataReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));
        colPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));

        gerarRelatorioButton.setOnMouseClicked((MouseEvent event) -> {

            String query = "SELECT cliente.nome AS cliente, reserva.cpfcliente AS cpf, pacote.nome AS pacote, " +
                    "pacote.preco, reserva.datareserva AS datareserva, reserva.statuspagamento AS statuspagamento " +
                    "FROM cliente, reserva, pacote " +
                    "WHERE reserva.cpfcliente = cliente.cpf AND reserva.pkfkpacoteid = pacote.pacoteID";


            ObservableList<Relatorio> relatorio = FXCollections.observableArrayList();


        try(Connection conn = new ConnectionFactory().getConnection(); Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query))
        {

            while(resultSet.next()){
                String nomeCliente = resultSet.getString("cliente");
                String cpf = resultSet.getString("cpf");
                String nomePacote = resultSet.getString("pacote");
                Double preco = resultSet.getDouble("preco");
                String data = resultSet.getString("datareserva");
                boolean pagamento = resultSet.getBoolean("statuspagamento");

                relatorio.add(new Relatorio(nomeCliente, nomePacote, cpf, data, preco, pagamento));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            tableRelatorio.setItems(relatorio);

        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });

        gerarItinerarioButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("gerarItinerario");
        });
    }
    private void fecha() {
        stage.close();
    }
}
