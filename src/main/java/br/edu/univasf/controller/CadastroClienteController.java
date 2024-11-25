package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.clienteDAO; // Importando o DAO
import br.edu.univasf.model.Cliente; // Importando o modelo Cliente
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class CadastroClienteController implements Initializable {

    @FXML
    public Button voltarButton;

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
    private TextArea enderecoTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrarClienteButton.setOnMouseClicked((MouseEvent event) -> {
            // Capturar os dados dos campos do formulário
            String nome = nomeTextField.getText();
            String cpf = cpfTextField.getText();
            String email = emailTextField.getText();
            String telefone = telefoneTextField.getText();
            String endereco = enderecoTextArea.getText();
            String dataNascimento = dataNascimentoPicker.getValue() != null ? dataNascimentoPicker.getValue().toString() : null;

            // Validação dos campos obrigatórios
            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || endereco.isEmpty() || dataNascimento == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText("Campos obrigatórios não preenchidos");
                alert.setContentText("Por favor, preencha todos os campos obrigatórios antes de cadastrar.");
                alert.show();
                return;
            }

            // Criar um objeto Cliente com os dados coletados
            Cliente cliente = new Cliente(cpf, nome, email, endereco, dataNascimento, "", "", "", telefone, "");

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

    private void fecha() {
        stage.close();
    }
}
