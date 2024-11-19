package br.edu.univasf.dao;

import br.edu.univasf.model.Pacote;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;

public class pacoteDAO{

    public void cadastrarPacote(Pacote pacote)
    {
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement preparedstatement;
        try{
            String finalQuery = "insert into pacote (nome, descrição, preço, destino, duração, disponibilidade) " + "values(?,?,?,?,?,?)";

            preparedstatement = conn.prepareStatement(finalQuery);
            preparedstatement.setString(1, pacote.getNome());
            preparedstatement.setString(2, pacote.getDescricao());
            preparedstatement.setDouble(3, pacote.getPreco());
            preparedstatement.setString(4, pacote.getDestino());
            preparedstatement.setInt(5, pacote.getDuracao());
            preparedstatement.setBoolean(6, pacote.getDisponibilidade());

            int rowsInserted = preparedstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção de pacote bem-sucedida!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}