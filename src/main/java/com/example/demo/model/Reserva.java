package com.example.demo.model;

import java.security.Timestamp;

public class Reserva {
    private int id_Reserva;
    private String cpf_Cliente;
    private Timestamp data;
    private int codigo_Pacote;
    private int quantidade_vagas;

    public Reserva() {}

    public Reserva(int id_Reserva, String cpf_Cliente, Timestamp data, int codigo_Pacote, int quantidade_vagas){
        this.id_Reserva = id_Reserva;
        this.cpf_Cliente = cpf_Cliente;
        this.data = data;
        this.codigo_Pacote = codigo_Pacote;
        this.quantidade_vagas = quantidade_vagas;
    }

    public int getId_Reserva() {
        return id_Reserva;
    }

    public String getCpf_Cliente() {
        return cpf_Cliente;
    }

    public Timestamp getData() {
        return data;
    }

    public int getCodigo_Pacote() {
        return codigo_Pacote;
    }

    public int getQuantidade_vagas() {
        return quantidade_vagas;
    }

    public void setId_Reserva(int id_Reserva) {
        this.id_Reserva = id_Reserva;
    }

    public void setCpf_Cliente(String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public void setCodigo_Pacote(int codigo_Pacote) {
        this.codigo_Pacote = codigo_Pacote;
    }

    public void setQuantidade_vagas(int quantidade_vagas) {
        this.quantidade_vagas = quantidade_vagas;
    }
}


