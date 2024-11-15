package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroClienteController {

    @FXML
    private TextField nomeCompletoField;

    @FXML
    private TextField cpfField;

    @FXML
    private DatePicker dataNascimentoPicker;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextArea enderecoField;

    @FXML
    private void cadastrarCliente() {
        String nomeCompleto = nomeCompletoField.getText();
        String cpf = cpfField.getText();
        String dataNascimento = (dataNascimentoPicker.getValue() != null) ? dataNascimentoPicker.getValue().toString() : "";
        String email = emailField.getText();
        String telefone = telefoneField.getText();
        String endereco = enderecoField.getText();

        System.out.println("Cliente cadastrado: " + nomeCompleto + ", CPF: " + cpf);
        System.out.println("Data de Nascimento: " + dataNascimento + ", E-mail: " + email);
        System.out.println("Telefone: " + telefone + ", Endereço: " + endereco);
    }
}
