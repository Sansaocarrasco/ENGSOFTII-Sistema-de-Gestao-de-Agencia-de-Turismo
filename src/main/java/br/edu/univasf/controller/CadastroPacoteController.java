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
    private TextField descricaoTextField;
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
                Date datainicio = java.sql.Date.valueOf(dataInicioPicker.getValue());
                Date datafim = java.sql.Date.valueOf(dataFimPicker.getValue());
                String preco = precoTextField.getText();
                int num_vagas = Integer.parseInt(vagasTextField.getText());
                boolean transporte = transporteCheckBox.isSelected();
                String hospedagem = hospedagemTextField.getText();
                String itinerario = atividadesTextArea.getText();
                String descricao = descricaoTextField.getText();

                // Criar um objeto Pacote com os dados coletados
                Pacote pacote = new Pacote(
                );

                // Criar o DAO e tentar inserir o pacote no banco de dados
                pacoteDAO dao = new pacoteDAO();
                dao.cadastrarPacote(pacote);  // Inserir pacote no banco de dados

                // Exibir alerta de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notificação");
                alert.setHeaderText(null);
                alert.setContentText("O Pacote foi cadastrado com sucesso na base de dados!");
                alert.show();

                // Exibir o ID gerado, caso você queira
                System.out.println("Pacote cadastrado com ID: " + pacote.getId());

            } catch (Exception e) {
                // Exibir alerta de erro se a inserção falhar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Falha na inserção");
                alert.setContentText("Não foi possível cadastrar o pacote no banco de dados.");
                alert.show();

                // Imprimir detalhes da exceção para depuração
                e.printStackTrace();  // Isso vai imprimir a stack trace no console, ajudando a identificar o erro
                System.out.println("Mensagem do erro: " + e.getMessage());  // Mensagem da exceção
            }
        });

        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });
    }
}
