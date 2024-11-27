package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.clienteDAO;
import br.edu.univasf.dao.pacoteDAO;
import br.edu.univasf.dao.reservaDAO;
import br.edu.univasf.model.Cliente;
import br.edu.univasf.model.Pacote;
import br.edu.univasf.model.Reserva;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class ReservaPacoteController implements Initializable {

    @FXML
    public Button voltarButton;
    @FXML
    public TextField idPacoteTextField;
    @FXML
    public TextField cpfClienteTextField;

    @FXML
    private Button reservaPacoteButton;

    @FXML
    private TextField nomePacoteTextField;

    @FXML
    private TextField nomeClienteTextField;

    @FXML
    private DatePicker dataReservaPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservaPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            // Captura os dados do formulário
            String nomeCliente = nomeClienteTextField.getText();
            String nomePacote = nomePacoteTextField.getText();

            // Verifica se a data de reserva foi preenchida
            if (dataReservaPicker.getValue() == null) {
                mostrarAlerta("Erro", "Data de Reserva", "A data de reserva deve ser preenchida.");
                return;
            }

            // Verifica se o nome do cliente foi fornecido
            if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
                mostrarAlerta("Erro", "Nome do Cliente", "O nome do cliente deve ser preenchido.");
                return;
            }

            // Captura o CPF do cliente
            String cpfCliente = cpfClienteTextField.getText();

            // Buscar o cliente pelo CPF
            Cliente cliente = buscarClientePorCpf(cpfCliente);

            // Verifica se o cliente foi encontrado
            if (cliente == null) {
                mostrarAlerta("Erro", "Cliente não encontrado", "Não foi possível encontrar o cliente com o CPF fornecido.");
                return;
            }

            // Buscar o pacote pelo nome ou ID
            pacoteDAO pacoteDAO = new pacoteDAO();
            Pacote pacote = null;

            if (!nomePacote.isEmpty()) {
                pacote = pacoteDAO.buscarPacotePorNome(nomePacote);  // Busca por nome
            } else if (!idPacoteTextField.getText().isEmpty()) {
                try {
                    Integer idPacote = Integer.parseInt(idPacoteTextField.getText());
                    pacote = pacoteDAO.buscarPacotePorId(idPacote);  // Busca por ID
                } catch (NumberFormatException e) {
                    mostrarAlerta("Erro", "ID do Pacote Inválido", "O ID fornecido não é válido.");
                    return;
                }
            }

            // Verifica se o pacote foi encontrado
            if (pacote == null || pacote.getId() == null) {
                mostrarAlerta("Erro", "Pacote não encontrado", "Não foi possível encontrar o pacote com o nome ou ID fornecido.");
                return;
            }

            // Verifica se já existe uma reserva do cliente para o mesmo pacote
            reservaDAO reservaDAO = new reservaDAO();
            boolean reservaExistente = false;
            try {
                reservaExistente = reservaDAO.verificarReservaExistente(cliente.getCpf(), pacote.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (reservaExistente) {
                mostrarAlerta("Erro", "Reserva já existente", "Você já tem uma reserva para este pacote.");
                return;
            }

            // Cria um objeto Reserva com os dados fornecidos
            Reserva reserva = new Reserva(
                    nomePacote,                          // Nome do pacote
                    pacote.getId(),                      // ID do pacote
                    cliente.getNome(),                   // Nome do cliente
                    cliente.getCpf(),                    // Usando o CPF do cliente
                    dataReservaPicker.getValue()         // Data da reserva
            );

            // Insere a reserva no banco de dados
            try {
                reservaDAO.insert_reserva(reserva); // Tenta salvar a reserva no banco

                // Exibe um alerta de sucesso
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Notificação");
                successAlert.setHeaderText(null);
                successAlert.setContentText("O Pacote foi reservado com sucesso!");
                successAlert.show();

                // Limpa os campos após a reserva
                limparCampos();

            } catch (SQLException e) {
                // Exibe um alerta caso ocorra algum erro ao salvar a reserva
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erro");
                errorAlert.setHeaderText("Falha na reserva");
                errorAlert.setContentText("Não foi possível realizar a reserva: " + e.getMessage());
                errorAlert.show();
            }
        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            // Retorna para a tela de opções ao clicar no botão "Voltar"
            Main.switchScreen("opcoes");
        });
    }

    // Método para exibir alertas
    private void mostrarAlerta(String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.show();
    }

    // Método para limpar os campos após a reserva
    private void limparCampos() {
        nomeClienteTextField.clear();
        idPacoteTextField.clear();
        cpfClienteTextField.clear();
        nomePacoteTextField.clear();
        dataReservaPicker.setValue(null);
    }

    // Método para buscar o cliente por CPF
    private Cliente buscarClientePorCpf(String cpf) {
        clienteDAO dao = new clienteDAO();
        Cliente cliente = dao.buscarClientePorCpf(cpf); // Buscando cliente no banco de dados
        return cliente;  // Retorna o cliente encontrado ou null se não existir
    }

    // Método para fechar a tela (caso necessário)
    private void fecha() {
        stage.close();
    }
}
