package br.edu.univasf.model;

import java.sql.Date;
import java.time.LocalDate;

public class Reserva {
    private Integer reservaID;
    private String nomePacote;
    private Integer pkfkPacoteID;
    private String nomeCliente;
    private Integer pkfkClienteId;
    private LocalDate datareserva;  // Usando LocalDate para melhor manipulação

    public Reserva(String nomePacote, Integer pkfkPacoteID, String nomeCliente, Integer pkfkClienteId, LocalDate datareserva) {
        this.nomePacote = nomePacote;
        this.pkfkPacoteID = pkfkPacoteID;
        this.nomeCliente = nomeCliente;
        this.pkfkClienteId = pkfkClienteId;
        this.datareserva = datareserva;
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

    public Integer getPkfkClienteId() {
        return pkfkClienteId;
    }

    public LocalDate getDatareserva() {
        return datareserva;  // Retorna LocalDate diretamente
    }

    public java.sql.Date getSqlDataReserva() {
        // Converte LocalDate para java.sql.Date quando necessário para persistência no banco de dados
        return java.sql.Date.valueOf(datareserva);
    }

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

    public void setPkfkClienteId(Integer pkfkClienteId) {
        this.pkfkClienteId = pkfkClienteId;
    }

    public void setDatareserva(LocalDate datareserva) {
        this.datareserva = datareserva;
    }
}
