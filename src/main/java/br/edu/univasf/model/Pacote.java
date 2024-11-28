package br.edu.univasf.model;

import java.util.Date;

public class Pacote {
    private Integer id;
    private String nome;
    private String destino;
    private Double preco;
    private String descricao;
    private int duracao;
    private boolean transporte;
    private String hospedagem;
    private String itinerario;
//    private int num_vagas;
//    private Date datainicio;
//    private Date datafim;

    // Construtores
    public Pacote() {
    }

    public Pacote(String nome, String destino, Double preco, String descricao, int duracao, boolean transporte, String hospedagem, String itinerario) {
        this.nome = nome;
        this.destino = destino;
        this.preco = preco;
        this.descricao = descricao;
        this.duracao = duracao;
        this.transporte = transporte;
        this.hospedagem = hospedagem;
        this.itinerario = itinerario;
    }


    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isTransporte() {
        return transporte;
    }

    public void setTransporte(boolean transporte) {
        this.transporte = transporte;
    }

    public String getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(String hospedagem) {
        this.hospedagem = hospedagem;
    }

    public int getDuracao() {return duracao; }

    public void setDuracao(int duracao) {this.duracao = duracao; }

    public String getItinerario() {return itinerario;}

    public void setItinerario(String itinerario) {this.itinerario = itinerario;}
}
