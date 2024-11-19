package br.edu.univasf.model;

import java.util.Date;

public class Pacote {
    private int id_Pacote;
    private String nome_Pacote;
    private float preco;
    private String descricao;
    private Date dataP;
    private float desconto;
    private int quantidade_vagas_totais;

    public Pacote() {
    }

    public Pacote(int id_Pacote, String nome_Pacote, float preco, String info, Date data, float desconto, int quantidade_vagas_totais) {
        this.id_Pacote = id_Pacote;
        this.nome_Pacote = nome_Pacote;
        this.preco = preco;
        this.descricao = info;
        this.dataP = data;
        this.desconto = desconto;
        this.quantidade_vagas_totais = quantidade_vagas_totais;
    }

    public int getId_Pacote() {
        return id_Pacote;
    }

    public String getNome_Pacote() {
        return nome_Pacote;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataP() {
        return dataP;
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

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataP(Date dataP) {
        this.dataP = dataP;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public void setQuantidade_vagas_totais(int quantidade_vagas_totais) {
        this.quantidade_vagas_totais = quantidade_vagas_totais;
    }
}
