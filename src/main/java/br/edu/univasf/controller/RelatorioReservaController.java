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
    public Button carregarDadosButton;
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

//        for(Relatorio relatorio1: relatorio){
//            System.out.println("Nome do Cliente: " + relatorio1.getNomeCliente());
//            System.out.println("Pacote: " + relatorio1.getNomePacote());
//            System.out.println("CPF: " + relatorio1.getCpf());
//            System.out.println("Data da Reserva: " + relatorio1.getDataReserva());
//            System.out.println("Preço: " + relatorio1.getPrecoReserva());
//            System.out.println("Pagamento: " + (relatorio1.isPagamento() ? "Pago" : "Não Pago"));
//            System.out.println("--------------------------------");
//        }

        //List<Relatorio> relatorio = new ArrayList<>();

        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });
    }
    private void fecha() {
        stage.close();
    }
}
