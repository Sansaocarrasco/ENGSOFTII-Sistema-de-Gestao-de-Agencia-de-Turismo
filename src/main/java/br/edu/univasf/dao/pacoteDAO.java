package br.edu.univasf.dao;

import br.edu.univasf.model.Pacote;
import br.edu.univasf.utils.ConnectionFactory;

import java.sql.*;

public class pacoteDAO {

    // Método para inserir um pacote no banco de dados
    public void cadastrarPacote(Pacote pacote) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = new ConnectionFactory().getConnection();

            // Query SQL para inserção (não passamos o 'id', pois é auto-incrementado)
            String finalQuery = "INSERT INTO pacote (nome, destino, datainicio, datafim, preco, num_vagas,transporte , hospedagem, itinerario, descricao) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(finalQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, pacote.getNome());
            preparedStatement.setString(2, pacote.getDestino());
            preparedStatement.setDate(3, new java.sql.Date(pacote.getDatainicio().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(pacote.getDatafim().getTime()));
            preparedStatement.setString(5, pacote.getPreco());
            preparedStatement.setInt(6, pacote.getNum_vagas());
            preparedStatement.setBoolean(7, pacote.isTransporte());
            preparedStatement.setString(8, pacote.getHospedagem());
            preparedStatement.setString(9, pacote.getItinerario());
            preparedStatement.setString(10, pacote.getDescricao());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(1);  // O primeiro campo é o ID gerado
                    pacote.setId(generatedId);  // Definir o ID no objeto Pacote
                    System.out.println("Inserção de pacote bem-sucedida! ID gerado: " + generatedId);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir pacote: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    // Método para buscar pacote por nome
    public Pacote buscarPacotePorNome(String nomePacote) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Pacote pacote = null;

        try {
            conn = new ConnectionFactory().getConnection();

            // Query SQL para buscar pacote pelo nome
            String query = "SELECT * FROM pacote WHERE nome = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nomePacote);

            resultSet = preparedStatement.executeQuery();

            // Se encontrar o pacote
            if (resultSet.next()) {
                pacote = new Pacote();
                pacote.setId(resultSet.getInt("id"));
                pacote.setNome(resultSet.getString("nome"));
                pacote.setDestino(resultSet.getString("destino"));
                pacote.setDatainicio(resultSet.getDate("datainicio"));
                pacote.setDatafim(resultSet.getDate("datafim"));
                pacote.setPreco(resultSet.getString("preco"));
                pacote.setNum_vagas(resultSet.getInt("num_vagas"));
                pacote.setTransporte(resultSet.getBoolean("transporte"));
                pacote.setHospedagem(resultSet.getString("hospedagem"));
                pacote.setItinerario(resultSet.getString("itinerario"));
                pacote.setDescricao(resultSet.getString("descricao"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacote por nome: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return pacote;  // Retorna o pacote encontrado ou null se não encontrar
    }
}
