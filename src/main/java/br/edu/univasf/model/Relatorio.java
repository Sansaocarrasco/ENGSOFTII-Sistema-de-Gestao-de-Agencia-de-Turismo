package br.edu.univasf.model;

public class Relatorio {

    private String nomeCliente;
    private String cpf;
    private String nomePacote;
    private String dataReserva;
    private Double precoReserva;
    private boolean pagamento;

    public Relatorio(String nomeCliente, String nomePacote, String cpf, String dataReserva, Double precoReserva, boolean pagamento) {
        this.nomeCliente = nomeCliente;
        this.nomePacote = nomePacote;
        this.cpf = cpf;
        this.dataReserva = dataReserva;
        this.precoReserva = precoReserva;
        this.pagamento = pagamento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Double getPrecoReserva() {
        return precoReserva;
    }

    public void setPrecoReserva(Double precoReserva) {
        this.precoReserva = precoReserva;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }
}
