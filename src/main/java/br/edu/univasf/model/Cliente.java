package br.edu.univasf.model;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String preferencias;
    private String historico;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String telefone, String email, String preferencias, String historico) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.preferencias = preferencias;
        this.historico = historico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public String getHistorico() {
        return historico;
    }
}