package br.edu.univasf.controller;

import br.edu.univasf.dao.pacoteDAO;
import br.edu.univasf.model.Pacote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class CadastroPacoteController implements Initializable {

    @FXML
    public TextField nomePacoteTextField;

    @FXML
    public TextField destinoTextField;

    @FXML
    public DatePicker dataInicioPicker;

    @FXML
    public DatePicker dataFimPicker;

    @FXML
    public TextField precoTextField;

    @FXML
    public TextField vagasTextField;

    @FXML
    public CheckBox transporteCheckBox;

    @FXML
    public TextField hospedagemTextField;

    @FXML
    public TextArea atividadesTextArea;

    @FXML
    public TextArea descricaoTextField;

    @FXML
    private Button cadastrarPacoteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrarPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            // Adicionando uma depuração inicial
            System.out.println("Botão de cadastro clicado!");

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

                if (dataInicioPicker.getValue() != null) {
                    datainicio = java.sql.Date.valueOf(dataInicioPicker.getValue());
                } else {
                    // Alerta para data inválida
                    System.out.println("Erro: Data de início não foi selecionada.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro de Validação");
                    alert.setHeaderText("Data de Início Inválida");
                    alert.setContentText("Por favor, selecione uma data de início válida.");
                    alert.show();
                    return;
                }

                if (dataFimPicker.getValue() != null) {
                    datafim = java.sql.Date.valueOf(dataFimPicker.getValue());
                } else {
                    // Alerta para data inválida
                    System.out.println("Erro: Data de fim não foi selecionada.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro de Validação");
                    alert.setHeaderText("Data de Fim Inválida");
                    alert.setContentText("Por favor, selecione uma data de fim válida.");
                    alert.show();
                    return;
                }

                // Criar um objeto Pacote com os dados coletados
                Pacote pacote = new Pacote(nome, destino, datainicio, datafim, preco,
                        itinerario, num_vagas, transporte, hospedagem,
                        itinerario, descricao);

                // Criar o DAO e tentar inserir o pacote no banco de dados
                pacoteDAO dao = new pacoteDAO();
                dao.cadastrarPacote(pacote);  // Inserir pacote no banco de dados

                // Exibir alerta de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notificação");
                alert.setHeaderText(null);
                alert.setContentText("O Pacote foi cadastrado com sucesso na base de dados!");
                alert.show();

                // Fechar a janela após a inserção
                fecha();
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
    }




    private void fecha() {
        stage.close();  // Fechar a janela do cadastro após a inserção
    }
}
