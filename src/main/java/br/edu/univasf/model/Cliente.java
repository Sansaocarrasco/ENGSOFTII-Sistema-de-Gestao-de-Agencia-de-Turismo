package br.edu.univasf.model;

public class Cliente {
    private Integer id;
    private String cpf;
    private String nome;
    private String email;
    private String rua;
    private String bairro;
    private String cidade;
    private String numero;
    private String estado;
    private String telefone;
    private String ddd;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, String email, String rua, String bairro, String cidade, String numero, String estado, String telefone, String ddd) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.estado = estado;
        this.telefone = telefone;
        this.ddd = ddd;
    }

    public Cliente(Integer id, String cpf, String nome, String email, String rua, String bairro, String cidade, String numero, String estado, String telefone, String ddd) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.estado = estado;
        this.telefone = telefone;
        this.ddd = ddd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}
