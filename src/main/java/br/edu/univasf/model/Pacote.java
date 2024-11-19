package br.edu.univasf.model;
import java.util.Date;

public class Pacote {
    private int id;
    private String nome;
    private String destino;
    private Date datainicio;
    private Date datafim;
    private String preco;
    private String itinerario;
    private int num_vagas;
    private boolean transporte;
    private String hospedagem;
    private String atividades;
    private String descricao;

    Pacote(){
    }

    public Pacote(String nome, String destino, Date datainicio, Date datafim, String preco,
                  String itinerario, int num_vagas, boolean transporte, String hospedagem,
                  String atividades, String descricao) {
        this.nome = nome;
        this.destino = destino;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.preco = preco;
        this.itinerario = itinerario;
        this.num_vagas = num_vagas;
        this.transporte = transporte;
        this.hospedagem = hospedagem;
        this.atividades = atividades;
        this.descricao = descricao;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDestino() {
        return destino;
    }

    public java.sql.Date getDatainicio() {
        return new java.sql.Date(datainicio.getTime());
    }

    public java.sql.Date getDatafim() {
        return new java.sql.Date(datafim.getTime());
    }

    public String getPreco() {
        return preco;
    }

    public String getItinerario() {
        return itinerario;
    }

    public int getNum_vagas() {
        return num_vagas;
    }

    public String getHospedagem() {
        return hospedagem;
    }

    public String getAtividades() {
        return atividades;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public void setHospedagem(String hospedagem) {
        this.hospedagem = hospedagem;
    }

    public void setNum_vagas(int num_vagas) {
        this.num_vagas = num_vagas;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTransporte() {
        return true;
    }
}
