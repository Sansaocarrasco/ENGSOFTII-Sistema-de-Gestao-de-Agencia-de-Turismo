package br.edu.univasf.dao;

import br.edu.univasf.model.Reserva;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;

public class reservaDAO{

    public void insert_reserva(Reserva reserva)
    {
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement preparedstatement;
        try{
            String finalQuery = "insert into reserva (pkfkClienteID, pkfkPacoteID, datareserva, parcelas, formaPagamento) " + "values(?,?,?,?,?)";

            preparedstatement = conn.prepareStatement(finalQuery);
            preparedstatement.setInt(1, reserva.getPkfkClienteId());
            preparedstatement.setInt(2, reserva.getPkfkPacoteID());
            //preparedstatement.setDate(3, Date.valueOf(reserva.getDatareserva()));
            preparedstatement.setDate(3, reserva.getDatareserva());
            preparedstatement.setInt(4, reserva.getParcelas());
            preparedstatement.setString(5, reserva.getFormaPagamento());

            int rowsInserted = preparedstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção de reserva bem-sucedida!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}