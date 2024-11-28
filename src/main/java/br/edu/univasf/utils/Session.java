package br.edu.univasf.utils;

import br.edu.univasf.model.Cliente;

public class Session {

    private static Cliente clienteAtual;

    // Define o cliente atual na sessão
    public static void setCliente(Cliente cliente) {
        clienteAtual = cliente;
    }

    // Obtém o cliente atual na sessão
    public static Cliente getCliente() {
        return clienteAtual;
    }

    // Verifica se o cliente está na sessão
    public static boolean isClienteDefinido() {
        return clienteAtual != null;
    }
}
