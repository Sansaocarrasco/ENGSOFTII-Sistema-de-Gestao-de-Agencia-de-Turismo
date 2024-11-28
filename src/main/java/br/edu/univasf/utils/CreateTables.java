package br.edu.univasf.utils;

import java.sql.*;

public class CreateTables {

    private static final String atributosCliente = "(clienteID SERIAL PRIMARY KEY, " +
            "cpf VARCHAR(11) NOT NULL UNIQUE, " +  // CPF do cliente
            "nome VARCHAR(100) NOT NULL, " +
            "email VARCHAR(100) NOT NULL UNIQUE, " +
            "rua VARCHAR(100) NOT NULL, " +
            "bairro VARCHAR(100) NOT NULL, " +
            "cidade VARCHAR(100) NOT NULL, " +
            "numero VARCHAR(20) NOT NULL, " +
            "estado VARCHAR(100) NOT NULL, " +
            "telefone VARCHAR(15) NOT NULL, " +
            "nascimento VARCHAR(10) NOT NULL)";  // Alterado para DATE

    private static final String atributosPacote = "(pacoteID SERIAL PRIMARY KEY, " +
            "nome VARCHAR(100) NOT NULL UNIQUE, " +
            "descricao VARCHAR, " +
            "preco NUMERIC(10, 2) NOT NULL, " +
            "destino VARCHAR(100) NOT NULL, " +
            "hospedagem VARCHAR(100), " +
            "duracao INT NOT NULL, " +
            "transporte BOOLEAN NOT NULL, " +
            "itinerario VARCHAR(255) NOT NULL)";

    private static final String atributosReserva = "(reservaID SERIAL PRIMARY KEY, " +
            "cpfCliente VARCHAR(11) NOT NULL, " +  // CPF do cliente
            "pkfkPacoteID INT NOT NULL, " +  // ID do pacote
            "nomeCliente VARCHAR(100) NOT NULL, " +  // Nome do cliente
            "nomePacote VARCHAR(100) NOT NULL, " +  // Nome do pacote
            "dataReserva DATE NOT NULL, " +  // Data da reserva
            "statusPagamento BOOLEAN NOT NULL, " +  // Número de parcelas
            "CONSTRAINT cliente_fk FOREIGN KEY (cpfCliente) REFERENCES cliente(cpf) ON DELETE CASCADE, " +  // FK para cliente usando CPF
            "CONSTRAINT pacote_fk FOREIGN KEY (pkfkPacoteID) REFERENCES pacote(pacoteID) ON DELETE CASCADE)";

    // Método para criar a tabela cliente
    public static void createCliente() {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            ConnectionFactory.createTable(conn, "cliente", atributosCliente);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela cliente: " + e.getMessage());
        }
    }

    // Método para criar a tabela pacote
    public static void createPacote() {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            ConnectionFactory.createTable(conn, "pacote", atributosPacote);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela pacote: " + e.getMessage());
        }
    }

    // Método para criar a tabela reserva
    public static void createReserva() {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            // Usando diretamente a variável 'atributosReserva' com as constraints já inclusas
            ConnectionFactory.createTable(conn, "reserva", atributosReserva);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela reserva: " + e.getMessage());
        }
    }
}
