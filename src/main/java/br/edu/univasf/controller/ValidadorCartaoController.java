package br.edu.univasf.controller;

import javafx.scene.control.Alert;

import java.time.YearMonth;

public class ValidadorCartaoController {

    // Método para validar o número do cartão usando o algoritmo de Luhn
    public static boolean validarNumeroCartao(String numeroCartao) {
        if (numeroCartao == null || !numeroCartao.matches("\\d{13,19}")) {
            return false; // Verifica se o número contém apenas dígitos e tem o tamanho adequado
        }

        int soma = 0;
        boolean alternar = false;

        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));

            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            soma += digito;
            alternar = !alternar;
        }

        return (soma % 10 == 0);
    }

    // Método para validar a data de validade
    public static boolean validarDataValidade(int mes, int ano) {
        YearMonth dataAtual = YearMonth.now();
        YearMonth dataValidade = YearMonth.of(ano, mes);

        return dataValidade.isAfter(dataAtual) || dataValidade.equals(dataAtual);
    }

    // Método para validar o CVV (Código de Segurança)
    public static boolean validarCVV(String cvv, String bandeira) {
        if (bandeira.equalsIgnoreCase("Amex")) {
            return cvv.matches("\\d{4}"); // American Express usa CVV de 4 dígitos
        }
        return cvv.matches("\\d{3}"); // Outras bandeiras usam CVV de 3 dígitos
    }

    // Método principal para validação completa
    public static boolean validarCartao(String numeroCartao, int mes, int ano, String cvv, String bandeira) {
        if (!validarNumeroCartao(numeroCartao)) {
            exibirAlerta("Número do cartão inválido.");
            return false;
        }

        if (!validarDataValidade(mes, ano)) {
            exibirAlerta("Data de validade inválida ou expirada.");
            return false;
        }

        if (!validarCVV(cvv, bandeira)) {
            exibirAlerta("CVV inválido para a bandeira do cartão.");
            return false;
        }

        return true;
    }

    // Método para exibir alertas
    private static void exibirAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Validação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
