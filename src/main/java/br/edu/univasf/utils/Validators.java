package br.edu.univasf.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.Objects;

public class Validators {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Remove caracteres não numéricos
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
        // Verifica se o número contém exatamente 11 dígitos
        return phoneNumber.matches("\\d{11}");
    }

    public static boolean isValidNumber(String numero) {

        return numero.matches("\\d+|\\d+[a-zA-Z]");
    }

    public static boolean isValidPrice(String price) {
        return price.matches("\\d*(\\.\\d{2})?");
    }

    public static boolean isOnlyLetters(String str) {
        return str.matches("^[a-zA-Z]+$");
    }

    public static boolean isValidDuration(String duration) {
        return duration.matches("\\d+");
    }

    public static boolean isValidCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verifica se o CPF tem exatamente 11 dígitos
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int soma1 = 0;
            for (int i = 0; i < 9; i++) {
                soma1 += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int primeiroDigito = (soma1 * 10) % 11;
            if (primeiroDigito == 10) primeiroDigito = 0;

            // Calcula o segundo dígito verificador
            int soma2 = 0;
            for (int i = 0; i < 10; i++) {
                soma2 += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int segundoDigito = (soma2 * 10) % 11;
            if (segundoDigito == 10) segundoDigito = 0;

            // Verifica os dígitos calculados com os informados
            return primeiroDigito == Character.getNumericValue(cpf.charAt(9)) &&
                    segundoDigito == Character.getNumericValue(cpf.charAt(10));
        } catch (NumberFormatException e) {
            return false; // CPF contém caracteres inválidos
        }
    }


    public static boolean isUnique(String table, String campo, String valor) {
        // Remove caracteres não numéricos do CPF
        if(Objects.equals(campo, "cpf"))
        {
            valor = valor.replaceAll("[^\\d]", "");
        }

        // Query para verificar a existência do CPF na tabela
        String query = "SELECT COUNT(*) FROM " + table + " WHERE " + campo + " = ?";

        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            // Define o parâmetro CPF na query
            preparedStatement.setString(1, valor);

            // Executa a consulta
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0; // Retorna true se não houver registros
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void mostrarAlerta(String titulo, String cabecalho, String mensagem, boolean isSuccess) {
        // Alerta com tipo de mensagem baseado no parâmetro `isSuccess`
        Alert alert;
        if (isSuccess) {
            alert = new Alert(Alert.AlertType.INFORMATION); // Alerta de sucesso
        } else {
            alert = new Alert(Alert.AlertType.ERROR); // Alerta de erro
        }

        // Definir título e texto do alerta
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);

        // Estilo customizado para o alerta
        alert.getDialogPane().setStyle("-fx-background-color: #f7f7f7; -fx-font-size: 14px; -fx-font-family: Arial;");
        alert.getButtonTypes().setAll(ButtonType.OK);

        // Configurar o botão de OK com estilo
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        if (isSuccess) {
            okButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;"); // Sucesso
        } else {
            okButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;"); // Erro
        }

        // Exibir o alerta
        alert.showAndWait();
    }



}

