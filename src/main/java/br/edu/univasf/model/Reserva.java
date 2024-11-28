package br.edu.univasf.model;

import java.time.LocalDate;

public class Reserva {
    private Integer reservaID;
    private String nomePacote;
    private Integer pkfkPacoteID;
    private String nomeCliente;
    private String cpfCliente;  // Alterado para CPF
    private LocalDate datareserva;  // Usando LocalDate para melhor manipulação
    private boolean statusPagamento;  // Adicionando o status do pagamento
    private Pacote pacote;  // Novo campo para armazenar o pacote


    // Atualizado para incluir o statusPagamento
    public Reserva(String nomePacote, Integer pkfkPacoteID, String nomeCliente, String cpfCliente, LocalDate datareserva, boolean statusPagamento) {
        this.nomePacote = nomePacote;
        this.pkfkPacoteID = pkfkPacoteID;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.datareserva = datareserva;
        this.statusPagamento = statusPagamento;
    }
    public Pacote getPacote() {
        return pacote;
    }
    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }


    public Integer getReservaID() {
        return reservaID;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public Integer getPkfkPacoteID() {
        return pkfkPacoteID;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;  // Retorna o CPF
    }

    public LocalDate getDatareserva() {
        return datareserva;  // Retorna LocalDate diretamente
    }

    public java.sql.Date getSqlDataReserva() {
        // Converte LocalDate para java.sql.Date quando necessário para persistência no banco de dados
        return java.sql.Date.valueOf(datareserva);
    }

    public boolean getStatusPagamento() {
        return statusPagamento;  // Retorna o status do pagamento
    }

    // Métodos setters
    public void setReservaID(Integer reservaID) {
        this.reservaID = reservaID;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public void setPkfkPacoteID(Integer pkfkPacoteID) {
        this.pkfkPacoteID = pkfkPacoteID;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;  // Altera o CPF
    }

    public void setDatareserva(LocalDate datareserva) {
        this.datareserva = datareserva;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;  // Altera o status do pagamento
    }
}
