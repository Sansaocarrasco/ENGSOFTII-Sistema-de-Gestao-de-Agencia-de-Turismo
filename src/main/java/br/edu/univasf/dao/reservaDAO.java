package br.edu.univasf.dao;

import br.edu.univasf.model.Pacote;
import br.edu.univasf.model.Reserva;
import br.edu.univasf.utils.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reservaDAO {

    // Método que insere uma nova reserva no banco de dados
    public void insert_reserva(Reserva reserva) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // Estabelecer a conexão com o banco
            conn = new ConnectionFactory().getConnection();
            String query = "INSERT INTO reserva (nomePacote, pkfkPacoteID, nomeCliente, cpfCliente, datareserva, statusPagamento) " +
                    "VALUES(?,?,?,?,?,?)";

            preparedStatement = conn.prepareStatement(query);

            // Atribui os parâmetros corretamente
            preparedStatement.setString(1, reserva.getNomePacote()); // Nome do pacote
            preparedStatement.setInt(2, reserva.getPkfkPacoteID()); // ID do pacote
            preparedStatement.setString(3, reserva.getNomeCliente()); // Nome do cliente
            preparedStatement.setString(4, reserva.getCpfCliente()); // CPF do cliente
            preparedStatement.setDate(5, java.sql.Date.valueOf(reserva.getDatareserva())); // Data da reserva
            preparedStatement.setBoolean(6, reserva.getStatusPagamento()); // Status do pagamento (false)

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


    public boolean verificarReservaExistente(String cpfCliente, Integer pkfkPacoteID) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = new ConnectionFactory().getConnection();
            String query = "SELECT COUNT(*) FROM reserva WHERE cpfCliente = ? AND pkfkPacoteID = ?";

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, cpfCliente);
            preparedStatement.setInt(2, pkfkPacoteID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;  // Retorna true se já houver uma reserva
            }

            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao verificar reserva existente: " + e.getMessage());
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<Reserva> buscarReservasPorCpf(String cpfCliente) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            String sql = "SELECT r.reservaID, r.pkfkPacoteID, r.cpfCliente, r.nomeCliente, r.nomePacote, " +
                    "r.dataReserva, r.statusPagamento " +
                    "FROM reserva r " +
                    "WHERE r.cpfCliente = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpfCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Cria a reserva a partir dos dados retornados
                Reserva reserva = new Reserva(
                        rs.getString("nomePacote"),
                        rs.getInt("pkfkPacoteID"),
                        rs.getString("nomeCliente"),
                        cpfCliente,
                        rs.getDate("dataReserva").toLocalDate(),
                        rs.getBoolean("statusPagamento")
                );
                reserva.setReservaID(rs.getInt("reservaID"));

                // Busca o pacote correspondente à reserva
                Pacote pacote = buscarPacotePorId(reserva.getPkfkPacoteID(), conn);
                reserva.setPacote(pacote);  // Associa o pacote à reserva

                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar reservas por CPF: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return reservas;
    }


    public Pacote buscarPacotePorId(int pacoteId, Connection conn) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Nome da coluna ajustado para 'pacoteID'
            String sql = "SELECT * FROM pacote WHERE pacoteID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pacoteId); // Substitui o parâmetro pelo pacoteId
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pacote(
                        rs.getString("nome"),          // Nome do pacote
                        rs.getString("destino"),       // Destino
                        rs.getDouble("preco"),         // Preço
                        rs.getString("descricao"),     // Descrição
                        rs.getInt("duracao"),          // Duração
                        rs.getBoolean("transporte"),   // Transporte incluso
                        rs.getString("hospedagem"),    // Hospedagem
                        rs.getString("itinerario")     // Itinerário
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacote por ID: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close(); // Fecha o ResultSet
            if (stmt != null) stmt.close(); // Fecha o PreparedStatement
        }

        return null; // Retorna null se nenhum pacote for encontrado
    }

    public void atualizarStatusPagamentoPorCliente(String cpfCliente) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = new ConnectionFactory().getConnection();

            // SQL para atualizar o status de pagamento das reservas de um cliente específico
            String query = "UPDATE reserva SET statusPagamento = true WHERE cpfCliente = ? AND statusPagamento = false";

            // Prepara a consulta
            preparedStatement = conn.prepareStatement(query);

            // Define o CPF do cliente como parâmetro
            preparedStatement.setString(1, cpfCliente);

            // Executa a atualização
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status de pagamento atualizado para as reservas do cliente " + cpfCliente);
            } else {
                System.out.println("Nenhuma reserva foi atualizada para o cliente " + cpfCliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status de pagamento para as reservas do cliente: " + e.getMessage());
            throw e;
        } finally {
            // Fecha os recursos
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
