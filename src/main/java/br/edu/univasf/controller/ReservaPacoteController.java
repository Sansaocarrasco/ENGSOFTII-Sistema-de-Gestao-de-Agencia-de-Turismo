package br.edu.univasf.controller;

import br.edu.univasf.Main;
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

            // Buscar o cliente
            Cliente cliente = buscarClientePorNome(nomeCliente);

            // Verifica se o cliente foi encontrado
            if (cliente == null) {
                mostrarAlerta("Erro", "Cliente não encontrado", "Não foi possível encontrar o cliente com o nome fornecido.");
                return;
            }

            // Buscar o pacote
            pacoteDAO pacoteDAO = new pacoteDAO();
            Pacote pacote = pacoteDAO.buscarPacotePorNome(nomePacote);

            // Verifica se o pacote foi encontrado e se o ID do pacote não é nulo
            if (pacote == null || pacote.getId() == null) {
                mostrarAlerta("Erro", "Pacote não encontrado", "Não foi possível encontrar o pacote com o nome fornecido ou o pacote está sem um ID válido.");
                return;
            }

            // Exibe os IDs em um alerta (opcional)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("IDs dos Itens");
            alert.setHeaderText("Informações da Reserva");
            alert.setContentText("ID do Cliente: " + cliente.getId() + "\nID do Pacote: " + pacote.getId());
            alert.show();

            // Cria um objeto Reserva com os dados fornecidos
            Reserva reserva = new Reserva(
                    nomePacote,                          // Nome do pacote
                    pacote.getId(),                      // ID do pacote (agora validado)
                    nomeCliente,                         // Nome do cliente
                    cliente.getId(),                     // ID do cliente
                    dataReservaPicker.getValue()         // Data da reserva
            );

            // Insere a reserva no banco de dados
            reservaDAO reservaDAO = new reservaDAO();
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

    // Método para buscar o cliente por nome (simulação de um método de busca)
    private Cliente buscarClientePorNome(String nomeCliente) {
        // Aqui, você pode substituir pela lógica real de busca de cliente
        // Exemplo simulado, onde o nome do cliente é usado para a busca.
        Cliente cliente = new Cliente();
        cliente.setId(1); // Atribuindo um ID fictício ao cliente encontrado
        cliente.setNome(nomeCliente);
        return cliente;  // Retorna o cliente encontrado
    }

    // Método para fechar a tela (caso necessário)
    private void fecha() {
        stage.close();
    }
}
