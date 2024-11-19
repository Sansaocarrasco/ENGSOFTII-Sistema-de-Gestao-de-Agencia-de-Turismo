package br.edu.univasf.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class CadastroClienteController implements Initializable {

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
            // Fazer a função de enviar as credenciais do cliente para o banco de dados.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificação");
            alert.setHeaderText(null);
            alert.setContentText("O Cliente foi cadastrado com sucesso na base de dados do Travel Manager!");
            alert.show();
            fecha();
        });
    }
    private void fecha() {
        stage.close();
    }
}
