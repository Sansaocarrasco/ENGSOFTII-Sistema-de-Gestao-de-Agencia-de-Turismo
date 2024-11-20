package br.edu.univasf.dao;

import br.edu.univasf.model.Reserva;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;

public class reservaDAO {

    // Método que insere uma nova reserva no banco de dados
    public void insert_reserva(Reserva reserva) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // Estabelecer a conexão com o banco
            conn = new ConnectionFactory().getConnection();
            String query = "INSERT INTO reserva (nomePacote, pkfkPacoteID, nomeCliente, pkfkClienteID, datareserva) " +
                    "VALUES(?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(query);

            // Atribui os parâmetros corretamente
            preparedStatement.setString(1, reserva.getNomePacote()); // Nome do pacote
            preparedStatement.setInt(2, reserva.getPkfkPacoteID()); // ID do pacote
            preparedStatement.setString(3, reserva.getNomeCliente()); // Nome do cliente
            preparedStatement.setInt(4, reserva.getPkfkClienteId()); // ID do cliente
            preparedStatement.setDate(5, java.sql.Date.valueOf(reserva.getDatareserva())); // Converte LocalDate para java.sql.Date

            // Executa a inserção
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção de reserva bem-sucedida!");
            } else {
                System.out.println("Falha ao inserir reserva.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir a reserva: " + e.getMessage());
            throw e;
        } finally {
            // Fecha a conexão e os recursos
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
