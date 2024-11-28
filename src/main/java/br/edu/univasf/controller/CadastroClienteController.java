package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.clienteDAO; // Importando o DAO
import br.edu.univasf.model.Cliente; // Importando o modelo Cliente
import br.edu.univasf.utils.Validators;
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
    private TextField ruaTextField;

    @FXML
    private TextField bairroTextField;

    @FXML
    private TextField cidadeTextField;

    @FXML
    private TextField numeroTextField;

    @FXML
    private TextField estadoTextField;


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
            String numero = numeroTextField.getText();
            String estado = estadoTextField.getText();
            String dataNascimento = dataNascimentoPicker.getValue() != null ? dataNascimentoPicker.getValue().toString() : null;

            cpf = cpf.replaceAll("[^\\d]", "");

            // Validação dos campos obrigatórios
            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || rua.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || numero.isEmpty() || estado.isEmpty() ||dataNascimento == null)
            {
                Validators.mostrarAlerta("Atenção", "Campos obrigatórios não preenchidos", "Por favor, preencha todos os campos obrigatórios antes de cadastrar.", false);
                return;
            }

            if(!Validators.isValidCPF(cpf))
            {
                Validators.mostrarAlerta("Atenção", "CPF inválido", "Por favor, preencha o campo com um cpf valido.", false);
                return;
            }

            if(!Validators.isValidNumber(numero))
            {
                Validators.mostrarAlerta("Atenção", "numero inválido", "Por favor, preencha o campo com um numero válido (Ex: 192 ou 200B).", false);
                return;
            }

            if(!Validators.isUnique("cliente","cpf", cpf))
            {
                Validators.mostrarAlerta("Atenção", "CPF inválido","já há um cliente cadastrado com o CPF informado.", false);
                return;
            }

            if(!Validators.isUnique("cliente", "email",  email ))
            {
                Validators.mostrarAlerta("Atenção", "Email inválido","já há um cliente cadastrado com o email informado.", false);
                return;
            }

            if(!Validators.isValidPhoneNumber(telefone))
            {
                Validators.mostrarAlerta("Erro", "telefone inválido", "Por favor, preencha o campo com um telefone válido.", false);
                return;
            }

            //verifica se não há números nos campos de endereço que não devem ter (descomentar na versão final)
//            if(!Validators.isOnlyLetters(rua) || !Validators.isOnlyLetters(cidade) || !Validators.isOnlyLetters(estado) || !Validators.isOnlyLetters(bairro))
//            {
//                Validators.mostrarAlerta("Erro", "campo de endereço inválido", "Por favor, verifique e preencha os campos com um endereço válido.");
//                return;
//            }

            // Criar um objeto Cliente com os dados coletados
            Cliente cliente = new Cliente(cpf, nome, email, rua, bairro, cidade, numero, estado, telefone, dataNascimento);

            // Criar o DAO e tentar inserir o cliente no banco de dados
            clienteDAO dao = new clienteDAO();
            try {
                dao.insert_cliente(cliente); // Inserir cliente no banco de dados

                // Exibir alerta de sucesso e limpar campos
                Validators.mostrarAlerta("Notificação", null,"O Cliente foi cadastrado com sucesso na base de dados!", true);
                nomeTextField.clear();
                cpfTextField.clear();
                emailTextField.clear();
                telefoneTextField.clear();
                ruaTextField.clear();
                bairroTextField.clear();
                cidadeTextField.clear();
                numeroTextField.clear();
                estadoTextField.clear();

                // Fechar a janela após a inserção
                //fecha();
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
