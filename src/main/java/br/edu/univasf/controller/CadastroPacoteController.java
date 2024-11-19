package br.edu.univasf.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

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
    private CheckBox transporteCheckBox;

    @FXML
    private TextField hospedagemTextField;

    @FXML
    private TextArea atividadesTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cadastrarPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            // Fazer a função de enviar as credenciais do pacote para o banco de dados.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificação");
            alert.setHeaderText(null);
            alert.setContentText("O Pacote foi cadastrado com sucesso na base de dados do Travel Manager!");
            alert.show();
            fecha();
        });
    }
    private void fecha() {
        stage.close();
    }
}
