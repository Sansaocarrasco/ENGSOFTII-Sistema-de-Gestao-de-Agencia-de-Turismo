package br.edu.univasf.model;

import java.sql.Date;

public class Reserva {
    private Integer reservaID;
    private Integer pkfkClienteId;
    private Integer pkfkPacoteID;
    private Date datareserva;
    private Integer parcelas;
    private String formaPagamento;

    public Reserva(Integer pkfkClienteId, Integer pkfkPacoteID, Date datareserva, Integer parcelas, String formaPagamento) {
        this.pkfkClienteId = pkfkClienteId;
        this.pkfkPacoteID = pkfkPacoteID;
        this.datareserva = datareserva;
        this.parcelas = parcelas;
        this.formaPagamento = formaPagamento;
    }

    public Reserva(Integer reservaID, Integer pkfkClienteId, Integer pkfkPacoteID, Date datareserva, Integer parcelas, String formaPagamento) {
        this.reservaID = reservaID;
        this.pkfkClienteId = pkfkClienteId;
        this.pkfkPacoteID = pkfkPacoteID;
        this.datareserva = datareserva;
        this.parcelas = parcelas;
        this.formaPagamento = formaPagamento;
    }

    public Integer getReservaID() {
        return reservaID;
    }

    public void setReservaID(Integer reservaID) {
        this.reservaID = reservaID;
    }

    public Integer getPkfkClienteId() {
        return pkfkClienteId;
    }

    public void setPkfkClienteId(Integer pkfkClienteId) {
        this.pkfkClienteId = pkfkClienteId;
    }

    public Integer getPkfkPacoteID() {
        return pkfkPacoteID;
    }

    public void setPkfkPacoteID(Integer pkfkPacoteID) {
        this.pkfkPacoteID = pkfkPacoteID;
    }

    public Date getDatareserva() {
        return datareserva;
    }

    public void setDatareserva(Date datareserva) {
        this.datareserva = datareserva;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}




