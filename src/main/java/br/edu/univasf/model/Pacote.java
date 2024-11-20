package br.edu.univasf.model;

import java.util.Date;

public class Pacote {
    private Integer id;
    private String nome;
    private String destino;
    private Date datainicio;
    private Date datafim;
    private String preco;
    private String descricao;
    private String itinerario;
    private int num_vagas;
    private boolean transporte;
    private String hospedagem;

    // Construtores
    public Pacote() {
    }

    public Pacote(String nome, String destino, Date datainicio, Date datafim, String preco,
                  String itinerario, int num_vagas, boolean transporte, String hospedagem,
                  String descricao) {
        this.nome = nome;
        this.destino = destino;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.preco = preco;
        this.itinerario = itinerario;
        this.num_vagas = num_vagas;
        this.transporte = transporte;
        this.hospedagem = hospedagem;
        this.descricao = descricao;
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

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public int getNum_vagas() {
        return num_vagas;
    }

    public void setNum_vagas(int num_vagas) {
        this.num_vagas = num_vagas;
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
}
