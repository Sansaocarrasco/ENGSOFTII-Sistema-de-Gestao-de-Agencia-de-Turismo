package br.edu.univasf.model;
import java.util.Date;

public class Pacote {
    private Integer id;
    private Integer duracao;
    private Double preco;
    private String nome;
    private String descricao;
    private String destino;
    private Boolean disponibilidade;

    Pacote(){

    }

    public Pacote(Integer duracao, Double preco, String nome, String descricao, String destino, Boolean disponibilidade) {
        this.duracao = duracao;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
        this.destino = destino;
        this.disponibilidade = disponibilidade;
    }

    Pacote(Integer id, String nome, String descricao, Double preco, String destino, Integer duracao, Boolean disponibilidade)
    {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.destino = destino;
        this.duracao = duracao;
        this.disponibilidade = disponibilidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
