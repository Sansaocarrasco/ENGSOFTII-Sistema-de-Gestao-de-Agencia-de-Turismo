package br.edu.univasf.dao;

import br.edu.univasf.model.Cliente;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;


public class clienteDAO{

    public void insert_cliente(Cliente cliente)
    {
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement preparedstatement;
        try{
            String finalQuery = "insert into cliente (cpf, nome, email, rua, bairro, cidade, numero, estado, telefone, ddd) " + "values(?,?,?,?,?,?,?,?,?,?)";

            preparedstatement = conn.prepareStatement(finalQuery);
            preparedstatement.setString(1, cliente.getCpf());
            preparedstatement.setString(2, cliente.getNome());
            preparedstatement.setString(3, cliente.getEmail());
            preparedstatement.setString(4, cliente.getRua());
            preparedstatement.setString(5, cliente.getBairro());
            preparedstatement.setString(6, cliente.getCidade());
            preparedstatement.setString(7, cliente.getNumero());
            preparedstatement.setString(8, cliente.getEstado());
            preparedstatement.setString(9, cliente.getTelefone());
            preparedstatement.setString(10, cliente.getDdd());

            int rowsInserted = preparedstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção de cliente bem-sucedida!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
