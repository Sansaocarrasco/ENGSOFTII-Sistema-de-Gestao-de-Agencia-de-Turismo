package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.pacoteDAO;
import br.edu.univasf.model.Pacote;
import br.edu.univasf.utils.Validators;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CadastroPacoteController implements Initializable {

    @FXML
    private Button cadastrarPacoteButton;
    @FXML
    private TextField nomePacoteTextField;
    @FXML
    private TextField destinoTextField;
    @FXML
    private TextField precoTextField;
    @FXML
    private TextField hospedagemTextField;
    @FXML
    private TextArea descricaoTextArea;
    @FXML
    private TextField duracaoTextField;
    @FXML
    private CheckBox transporteCheckBox;
    @FXML
    private TextArea itinerarioTextArea;
    @FXML
    private Button voltarButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrarPacoteButton.setOnMouseClicked((MouseEvent event) -> {

            try {
                // Capturar os dados dos campos do formulário
                String nome = nomePacoteTextField.getText();
                String destino = destinoTextField.getText();
                String preco = precoTextField.getText();
                String duracao = duracaoTextField.getText();
                String hospedagem = hospedagemTextField.getText();
                String descricao = descricaoTextArea.getText();
                String itinerario = itinerarioTextArea.getText();
                boolean transporte = transporteCheckBox.isSelected();


                // Validar campos obrigatórios
                if (nome.isEmpty() || destino.isEmpty() || descricao.isEmpty() || preco.isEmpty() || duracao.isEmpty() || itinerario.isEmpty()) {
                    Validators.mostrarAlerta("Atenção", "Campos obrigatórios não preenchidos","Por favor, preencha todos os campos obrigatórios antes de cadastrar.", false);
                    return;
                }

                if(!Validators.isUnique("pacote", "nome", nome))
                {
                    Validators.mostrarAlerta("Atenção", "nome inválido", "já há um pacote cadastrado com este nome.", false);
                    return;
                }

                if(!Validators.isValidPrice(preco))
                {
                    Validators.mostrarAlerta("Atenção", "preço inválido", "forneça um valor de preço válido", false);
                    return;
                }

                if(!Validators.isValidDuration(duracao))
                {
                    Validators.mostrarAlerta("Atenção", "duração inválida", "forneça uma quantidade de dias válida",false);
                    return;
                }

                // Converter duracao para inteiro
                int dura = Integer.parseInt(duracao);
                double prec = Double.parseDouble(preco);

                // Criar e inicializar o objeto Pacote
                Pacote pacote = new Pacote(nome, destino, prec, descricao, dura, transporte, hospedagem, itinerario);

                // Criar o DAO e tentar inserir o pacote no banco de dados
                pacoteDAO dao = new pacoteDAO();
                dao.cadastrarPacote(pacote);  // Inserir pacote no banco de dados

                // Exibir alerta de sucesso e limpar campos
                Validators.mostrarAlerta("Notificação", null, "O Pacote foi cadastrado com sucesso na base de dados!", true);
//                descricaoTextArea.clear();
//                destinoTextField.clear();
//                duracaoTextField.clear();
//                hospedagemTextField.clear();
//                precoTextField.clear();
//                itinerarioTextArea.clear();
//                nomePacoteTextField.clear();

            } catch (Exception e) {
                // Exibir alerta de erro se a inserção falhar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Falha na inserção");
                alert.setContentText("Não foi possível cadastrar o pacote no banco de dados.");
                alert.show();

                // Imprimir detalhes da exceção para depuração
                e.printStackTrace();
                System.out.println("Mensagem do erro: " + e.getMessage());
            }
        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });
    }
}
