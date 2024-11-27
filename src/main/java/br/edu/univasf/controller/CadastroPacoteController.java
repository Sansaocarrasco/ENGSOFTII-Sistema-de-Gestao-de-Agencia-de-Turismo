package br.edu.univasf.controller;

import br.edu.univasf.Main;
import br.edu.univasf.dao.pacoteDAO;
import br.edu.univasf.model.Pacote;
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
    private DatePicker dataInicioPicker;
    @FXML
    private DatePicker dataFimPicker;
    @FXML
    private TextField precoTextField;
    @FXML
    private TextField vagasTextField;
    @FXML
    private TextField hospedagemTextField;
    @FXML
    private TextArea atividadesTextArea;
    @FXML
    private TextArea descricaoTextArea;
    @FXML
    private CheckBox transporteCheckBox;
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
                String vagas = vagasTextField.getText();
                String hospedagem = hospedagemTextField.getText();
                String itinerario = atividadesTextArea.getText();
                String descricao = descricaoTextArea.getText();
                boolean transporte = transporteCheckBox.isSelected();

                // Validar campos obrigatórios
                if (nome.isEmpty() || destino.isEmpty() ||
                        dataInicioPicker.getValue() == null ||
                        dataFimPicker.getValue() == null ||
                        itinerario.isEmpty() || descricao.isEmpty() ||
                        preco.isEmpty() || vagas.isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atenção");
                    alert.setHeaderText("Campos obrigatórios não preenchidos");
                    alert.setContentText("Por favor, preencha todos os campos obrigatórios antes de cadastrar.");
                    alert.show();
                    return;
                }

                // Verificar se já existe um pacote com o mesmo nome no banco de dados
                pacoteDAO dao = new pacoteDAO();
                boolean pacoteExistente = dao.verificarPacoteExistente(nome); // Verifica se o nome do pacote já está cadastrado no banco

                if (pacoteExistente) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atenção");
                    alert.setHeaderText("Pacote já cadastrado");
                    alert.setContentText("Já existe um pacote com o nome informado. Escolha outro nome.");
                    alert.show();
                    return;
                }

                // Obter as datas como objetos SQL Date
                Date datainicio = Date.valueOf(dataInicioPicker.getValue());
                Date datafim = Date.valueOf(dataFimPicker.getValue());

                // Converter vagas para inteiro
                int num_vagas = Integer.parseInt(vagas);

                // Criar e inicializar o objeto Pacote
                Pacote pacote = new Pacote(
                        nome, destino, datainicio, datafim, preco,
                        itinerario, num_vagas, transporte, hospedagem, descricao
                );

                // Inserir pacote no banco de dados
                dao.cadastrarPacote(pacote);

                // Exibir alerta de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notificação");
                alert.setHeaderText(null);
                alert.setContentText("O Pacote foi cadastrado com sucesso na base de dados!");
                alert.show();

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
