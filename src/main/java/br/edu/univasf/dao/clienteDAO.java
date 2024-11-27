package br.edu.univasf.dao;

import br.edu.univasf.model.Cliente;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;

public class clienteDAO {

    // Inserir Cliente
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

            // Conversão de LocalDate para java.sql.Date
            if (cliente.getDataNascimento() != null) {
                preparedStatement.setDate(10, Date.valueOf(cliente.getDataNascimento()));
            } else {
                preparedStatement.setDate(10, null);
            }

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção de cliente bem-sucedida!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    // Buscar Cliente
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
                // Corrigindo a criação do Cliente, extraindo os dados do resultSet

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
                        resultSet.getDate("data_nascimento") != null ? resultSet.getDate("data_nascimento").toLocalDate() : null
                );
                cliente.setId(resultSet.getInt("id"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setRua(resultSet.getString("rua"));
                cliente.setBairro(resultSet.getString("bairro"));
                cliente.setCidade(resultSet.getString("cidade"));
                cliente.setNumero(resultSet.getString("numero"));
                cliente.setEstado(resultSet.getString("estado"));
                cliente.setTelefone(resultSet.getString("telefone"));
                // A data de nascimento precisa ser convertida para LocalDate
                cliente.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
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
