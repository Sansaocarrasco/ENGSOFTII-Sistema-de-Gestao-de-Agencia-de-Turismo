package br.edu.univasf.utils;

import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;

public class CreateTables {

    private static String atributosCliente = "(clienteID SERIAL primary key, cpf varchar, nome varchar not null, email varchar, rua varchar, bairro varchar, cidade varchar, numero varchar, estado varchar, telefone varchar not null, DDD varchar not null )";
    private static String atributosPacote = "(pacoteID SERIAL primary key, nome varchar not null, descrição varchar, preço real not null, destino varchar not null, duração int not null, disponibilidade bool not null )";
    private static String atributosReserva = "(reservaID SERIAL, pkfkClienteID int, pkfkPacoteID int, dataReserva date, parcelas int not null, formaPagamento varchar not null, ";
    private static String constraintReserva1 = "constraint reserva_pk Primary Key (reservaID, pkfkClienteID, pkfkPacoteID, dataReserva),";
    private static String constraintReserva2 = "constraint cliente_fk Foreign Key (pkfkClienteID) references cliente(clienteID),";
    private static String constraintReserva3 = "constraint pacote_fk Foreign Key (pkfkPacoteID) references pacote(pacoteID)";
    private static String reserva = atributosReserva + constraintReserva1 + constraintReserva2 + constraintReserva3 + " )";

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
            ConnectionFactory.createTable(conn, "reserva", reserva);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
