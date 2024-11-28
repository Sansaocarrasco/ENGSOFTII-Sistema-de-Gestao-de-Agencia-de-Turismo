package br.edu.univasf.dao;

import br.edu.univasf.model.Pacote;
import br.edu.univasf.utils.ConnectionFactory;

import java.sql.*;

public class pacoteDAO {

    public boolean verificarPacoteExistente(String nome) {
        Pacote pacote = buscarPacotePorNome(nome);
        return pacote != null;  // Se encontrar um pacote, retorna true (já existe)
    }

    // Método para inserir um pacote no banco de dados
    public void cadastrarPacote(Pacote pacote) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = new ConnectionFactory().getConnection();


            // Query SQL para inserção (não passamos o 'id', pois é auto-incrementado)
            String finalQuery = "INSERT INTO pacote (nome, descricao, preco, destino, hospedagem, duracao, transporte, itinerario) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(finalQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, pacote.getNome());
            preparedStatement.setString(2, pacote.getDescricao());
            preparedStatement.setDouble(3, pacote.getPreco());
            preparedStatement.setString(4, pacote.getDestino());
            preparedStatement.setString(5, pacote.getHospedagem());
            preparedStatement.setInt(6, pacote.getDuracao());
            preparedStatement.setBoolean(7, pacote.isTransporte());
            preparedStatement.setString(8, pacote.getItinerario());

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
                pacote.setId(resultSet.getInt("pacoteid"));
                pacote.setNome(resultSet.getString("nome"));
                pacote.setDestino(resultSet.getString("destino"));
                pacote.setPreco(resultSet.getDouble("preco"));
                pacote.setDuracao(resultSet.getInt("duracao"));
                pacote.setTransporte(resultSet.getBoolean("transporte"));
                pacote.setHospedagem(resultSet.getString("hospedagem"));
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

    public Pacote buscarPacotePorId(Integer idPacote) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Pacote pacote = null;

        try {
            conn = new ConnectionFactory().getConnection();

            // Query SQL para buscar pacote pelo id
            String query = "SELECT * FROM pacote WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, idPacote);

            resultSet = preparedStatement.executeQuery();

            // Se encontrar o pacote
            if (resultSet.next()) {
                pacote = new Pacote();
                pacote.setId(resultSet.getInt("pacoteid"));
                pacote.setNome(resultSet.getString("nome"));
                pacote.setDestino(resultSet.getString("destino"));;
                pacote.setPreco(resultSet.getDouble("preco"));
                pacote.setDuracao(resultSet.getInt("duracao"));
                pacote.setTransporte(resultSet.getBoolean("transporte"));
                pacote.setHospedagem(resultSet.getString("hospedagem"));
                pacote.setItinerario(resultSet.getString("itinerario"));
                pacote.setDescricao(resultSet.getString("descricao"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacote por id: " + e.getMessage());
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
