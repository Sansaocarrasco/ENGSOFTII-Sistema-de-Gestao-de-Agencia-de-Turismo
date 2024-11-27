package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.clienteDAO; // Importando o DAO
import br.edu.univasf.model.Cliente; // Importando o modelo Cliente
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class CadastroClienteController implements Initializable {

    @FXML
    public Button voltarButton;

    @FXML
    public TextField ruaTextField;

    @FXML
    private Button cadastrarClienteButton;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cpfTextField;

    @FXML
    private DatePicker dataNascimentoPicker;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private TextField bairroTextField;

    @FXML
    private TextField cidadeTextField;

    @FXML
    private TextField estadoTextField;

    @FXML
    private TextField numeroTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrarClienteButton.setOnMouseClicked((MouseEvent event) -> {
            // Capturar os dados dos campos do formulário
            String nome = nomeTextField.getText();
            String cpf = cpfTextField.getText();
            String email = emailTextField.getText();
            String telefone = telefoneTextField.getText();
            String rua = ruaTextField.getText();
            String bairro = bairroTextField.getText();
            String cidade = cidadeTextField.getText();
            String estado = estadoTextField.getText();
            String numero = numeroTextField.getText();
            LocalDate dataNascimento = dataNascimentoPicker.getValue();

            // Verificação se algum campo obrigatório está vazio
            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty() || rua.isEmpty() ||
                    bairro.isEmpty() || cidade.isEmpty() || estado.isEmpty() || numero.isEmpty() || dataNascimento == null) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText("Campos obrigatórios não preenchidos");
                alert.setContentText("Por favor, preencha todos os campos obrigatórios antes de cadastrar.");
                alert.show();
                return; // Interrompe o fluxo caso algum campo esteja vazio
            }

            // Verificar se o CPF já está cadastrado
            if (verificarCpfExistente(cpf)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText("CPF já cadastrado");
                alert.setContentText("O CPF fornecido já está cadastrado no sistema.");
                alert.show();
                return; // Interrompe o fluxo caso o CPF já exista
            }

            // Criar um objeto Cliente com os dados coletados
            Cliente cliente = new Cliente(cpf, nome, email, telefone, rua, bairro, cidade, numero, estado, dataNascimento);

            // Criar o DAO e tentar inserir o cliente no banco de dados
            clienteDAO dao = new clienteDAO();
            try {
                dao.insert_cliente(cliente); // Inserir cliente no banco de dados

                // Exibir alerta de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notificação");
                alert.setHeaderText(null);
                alert.setContentText("O Cliente foi cadastrado com sucesso na base de dados!");
                alert.show();

                // Fechar a janela após a inserção
                fecha();
            } catch (Exception e) {
                // Exibir alerta de erro se a inserção falhar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Falha na inserção");
                alert.setContentText("Não foi possível cadastrar o cliente no banco de dados.");
                alert.show();
                e.printStackTrace();
            }
        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            // Retorna para a tela de opções ao clicar no botão "Voltar"
            Main.switchScreen("opcoes");
        });
    }

    // Método para verificar se o CPF já está cadastrado no banco de dados
    private boolean verificarCpfExistente(String cpf) {
        clienteDAO dao = new clienteDAO();
        Cliente cliente = dao.buscarClientePorCpf(cpf); // Buscar cliente no banco de dados pelo CPF
        return cliente != null; // Retorna verdadeiro se o cliente já existir
    }

    private void fecha() {
        stage.close();
    }
}
