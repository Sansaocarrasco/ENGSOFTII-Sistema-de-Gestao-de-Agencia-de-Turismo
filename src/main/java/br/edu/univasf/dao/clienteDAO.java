package br.edu.univasf.dao;

import br.edu.univasf.model.Cliente;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;

public class clienteDAO {

    // Método para inserir um cliente no banco de dados
    public void insert_cliente(Cliente cliente) {
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement preparedStatement;
        try {
            String finalQuery = "INSERT INTO cliente (cpf, nome, email, rua, bairro, cidade, numero, estado, telefone, data_nascimento) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(finalQuery);
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getRua());
            preparedStatement.setString(5, cliente.getBairro());
            preparedStatement.setString(6, cliente.getCidade());
            preparedStatement.setString(7, cliente.getNumero());
            preparedStatement.setString(8, cliente.getEstado());
            preparedStatement.setString(9, cliente.getTelefone());
            preparedStatement.setString(10, cliente.getNascimento());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção de cliente bem-sucedida!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Método para buscar cliente por nome
    public Cliente buscarClientePorNome(String nome) {
        Cliente cliente = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = new ConnectionFactory().getConnection();
            String query = "SELECT * FROM cliente WHERE nome = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nome);

            resultSet = preparedStatement.executeQuery();

            // Se encontrar um cliente com o nome fornecido
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));  // Supondo que "id" seja a chave primária
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setRua(resultSet.getString("rua"));
                cliente.setBairro(resultSet.getString("bairro"));
                cliente.setCidade(resultSet.getString("cidade"));
                cliente.setNumero(resultSet.getString("numero"));
                cliente.setEstado(resultSet.getString("estado"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setNascimento(resultSet.getString("data_nascimento"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por nome: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return cliente;  // Retorna o cliente encontrado ou null se não encontrado
    }

    public Cliente buscarClientePorCpf(String cpf) {
        Cliente cliente = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = new ConnectionFactory().getConnection();
            String query = "SELECT * FROM cliente WHERE cpf = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, cpf);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cliente = new Cliente(
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("rua"),
                        resultSet.getString("bairro"),
                        resultSet.getString("cidade"),
                        resultSet.getString("numero"),
                        resultSet.getString("estado"),
                        resultSet.getString("nascimento")
                );
                cliente.setId(resultSet.getInt("clienteid"));  // Alterado para 'clienteid'
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por CPF: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return cliente;
    }

}
