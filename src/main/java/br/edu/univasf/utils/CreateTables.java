package br.edu.univasf.utils;

import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;

public class CreateTables {

    private static String atributosCliente = "(clienteID SERIAL PRIMARY KEY, cpf VARCHAR, nome VARCHAR NOT NULL, email VARCHAR, rua VARCHAR, bairro VARCHAR, cidade VARCHAR, numero VARCHAR, estado VARCHAR, telefone VARCHAR NOT NULL, DDD VARCHAR NOT NULL)";
    private static String atributosPacote = "(pacoteID SERIAL PRIMARY KEY, nome VARCHAR NOT NULL, descricao VARCHAR, preco NUMERIC(10, 2) NOT NULL, destino VARCHAR NOT NULL, duracao INT NOT NULL, disponibilidade BOOLEAN NOT NULL)";
    private static String atributosReserva = "(reservaID SERIAL PRIMARY KEY, pkfkClienteID INT, pkfkPacoteID INT, dataReserva DATE, parcelas INT NOT NULL, formaPagamento VARCHAR NOT NULL)";
    private static String constraintReserva1 = "CONSTRAINT cliente_fk FOREIGN KEY (pkfkClienteID) REFERENCES cliente(clienteID),";
    private static String constraintReserva2 = "CONSTRAINT pacote_fk FOREIGN KEY (pkfkPacoteID) REFERENCES pacote(pacoteID)";

    public static void createCliente() {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            ConnectionFactory.createTable(conn, "cliente", atributosCliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createPacote() {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            ConnectionFactory.createTable(conn, "pacote", atributosPacote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createReserva() {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            String reserva = atributosReserva + constraintReserva1 + constraintReserva2;
            ConnectionFactory.createTable(conn, "reserva", reserva);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
