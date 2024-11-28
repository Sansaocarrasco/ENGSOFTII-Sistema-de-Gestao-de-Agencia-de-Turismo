package br.edu.univasf.controller;

public class ValidadorContaBancariaController {

    // Função para validar o número da conta e a agência
    public boolean validarContaBancaria(String numeroConta, String agencia) {
        // Valida se o número da conta e a agência não são vazios e têm o tamanho esperado
        if (numeroConta == null || agencia == null) {
            return false;
        }

        // Verifica se o número da conta e a agência têm o tamanho esperado (exemplo: 6 dígitos para agência e 12 para a conta)
        if (numeroConta.length() != 12 || agencia.length() != 6) {
            return false;
        }

        // Aqui você pode adicionar outras verificações, como verificação de números válidos, etc.

        // Se tudo estiver correto, retorna true
        return true;
    }
}
