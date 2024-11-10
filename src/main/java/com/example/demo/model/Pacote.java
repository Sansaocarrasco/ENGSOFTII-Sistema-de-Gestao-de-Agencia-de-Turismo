package com.example.demo.model;

import java.util.Date;

public class Pacote {
    private int id_Pacote;
    private String nome_Pacote;
    private double preco_Pacote;
    private String info_Pacote;
    private Date data_Pacote;
    private float desconto;
    private int quantidade_vagas_totais;

    public Pacote() {
    }

    public Pacote(int id_Pacote, String nome_Pacote, double preco_Pacote, String info_Pacote, Date data_Pacote, float desconto, int quantidade_vagas_totais) {
        this.id_Pacote = id_Pacote;
        this.nome_Pacote = nome_Pacote;
        this.preco_Pacote = preco_Pacote;
        this.info_Pacote = info_Pacote;
        this.data_Pacote = data_Pacote;
        this.desconto = desconto;
        this.quantidade_vagas_totais = quantidade_vagas_totais;
    }

    public int getId_Pacote() {
        return id_Pacote;
    }

    public String getNome_Pacote() {
        return nome_Pacote;
    }

    public double getPreco_Pacote() {
        return preco_Pacote;
    }

    public String getInfo_Pacote() {
        return info_Pacote;
    }

    public Date getData_Pacote() {
        return data_Pacote;
    }

    public float getDesconto() {
        return desconto;
    }

    public int getQuantidade_vagas_totais() {
        return quantidade_vagas_totais;
    }

    public void setId_Pacote(int id_Pacote) {
        this.id_Pacote = id_Pacote;
    }

    public void setNome_Pacote(String nome_Pacote) {
        this.nome_Pacote = nome_Pacote;
    }

    public void setPreco_Pacote(double preco_Pacote) {
        this.preco_Pacote = preco_Pacote;
    }

    public void setInfo_Pacote(String info_Pacote) {
        this.info_Pacote = info_Pacote;
    }

    public void setData_Pacote(Date data_Pacote) {
        this.data_Pacote = data_Pacote;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public void setQuantidade_vagas_totais(int quantidade_vagas_totais) {
        this.quantidade_vagas_totais = quantidade_vagas_totais;
    }
}
