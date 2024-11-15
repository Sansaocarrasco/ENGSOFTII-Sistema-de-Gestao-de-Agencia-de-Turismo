package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroPacoteController {

    @FXML
    private TextField nomePacoteField;

    @FXML
    private TextField destinoField;

    @FXML
    private DatePicker dataInicioPicker;

    @FXML
    private DatePicker dataFimPicker;

    @FXML
    private TextField precoField;

    @FXML
    private TextField vagasField;

    @FXML
    private CheckBox transporteCheckBox;

    @FXML
    private TextField hospedagemField;

    @FXML
    private TextArea atividadesField;

    @FXML
    private void cadastrarPacote() {
        String nomePacote = nomePacoteField.getText();
        String destino = destinoField.getText();
        String dataInicio = (dataInicioPicker.getValue() != null) ? dataInicioPicker.getValue().toString() : "";
        String dataFim = (dataFimPicker.getValue() != null) ? dataFimPicker.getValue().toString() : "";
        String preco = precoField.getText();
        String vagas = vagasField.getText();
        boolean incluiTransporte = transporteCheckBox.isSelected();
        String hospedagem = hospedagemField.getText();
        String atividades = atividadesField.getText();

        System.out.println("Pacote cadastrado: " + nomePacote + ", Destino: " + destino);
        System.out.println("Data de Início: " + dataInicio + ", Data de Fim: " + dataFim);
        System.out.println("Preço: " + preco + ", Vagas: " + vagas + ", Inclui Transporte: " + incluiTransporte);
        System.out.println("Hospedagem: " + hospedagem + ", Atividades: " + atividades);
    }
}
