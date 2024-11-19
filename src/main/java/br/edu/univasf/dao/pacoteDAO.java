package br.edu.univasf.dao;

import br.edu.univasf.model.Pacote;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.SQLException;

import java.sql.*;

public class pacoteDAO {

    public void cadastrarPacote(Pacote pacote) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            // Estabelecer a conexão com o banco
            conn = new ConnectionFactory().getConnection();

            // Query SQL para inserção (não passamos o 'id', pois é auto-incrementado)
            String finalQuery = "INSERT INTO pacote (nome, destino, datainicio, datafim, preco, num_vagas, hospedagem, itinerario, descricao, transporte) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(finalQuery);

            preparedStatement.setString(1, pacote.getNome());
            preparedStatement.setString(2, pacote.getDestino());
            preparedStatement.setDate(3, new java.sql.Date(pacote.getDatainicio().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(pacote.getDatafim().getTime()));
            preparedStatement.setString(5, pacote.getPreco());
            preparedStatement.setInt(6, pacote.getNum_vagas());
            preparedStatement.setString(7, pacote.getHospedagem());
            preparedStatement.setString(8, pacote.getItinerario());
            preparedStatement.setString(9, pacote.getDescricao());
            preparedStatement.setBoolean(10, pacote.isTransporte());

            // Executar a inserção
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Inserção de pacote bem-sucedida!");
            }

        } catch (SQLException e) {
            // Tratar erros de SQL
            System.out.println("Erro ao inserir pacote: " + e.getMessage());
        } finally {
            // Fechar a conexão e o prepared statement
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
