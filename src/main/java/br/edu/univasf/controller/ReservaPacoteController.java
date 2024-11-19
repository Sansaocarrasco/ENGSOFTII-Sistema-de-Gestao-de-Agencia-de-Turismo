package br.edu.univasf.controller;

import br.edu.univasf.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class ReservaPacoteController implements Initializable {

    @FXML
    public Button voltarButton;

    @FXML
    private Button reservaPacoteButton;

    @FXML
    private TextField nomePacoteTextField;

    @FXML
    private TextField destinoPacoteTextField;

    @FXML
    private DatePicker dataInicioPacotePicker;

    @FXML
    private DatePicker dataFimPacotePicker;

    @FXML
    private TextField precoPacoteTextField;

    // Dados do Cliente

    @FXML
    private TextField nomeClienteTextField;

    @FXML
    private TextField emailClienteTextFields;

    @FXML
    private TextField telefoneClienteTextField;


    // Dados da Reserva

    @FXML
    private DatePicker dataReservaPicker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservaPacoteButton.setOnMouseClicked((MouseEvent event) -> {
            // Fazer a função de enviar as credenciais do pacote para o banco de dados.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificação");
            alert.setHeaderText(null);
            alert.setContentText("O Pacote foi reservado com sucesso!");
            // Fazer opção para reservar outro pacote!!
            alert.show();
        });
        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });
    }
    private void fecha() {
        stage.close();
    }
}
