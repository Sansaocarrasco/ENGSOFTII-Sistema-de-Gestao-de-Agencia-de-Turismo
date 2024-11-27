package br.edu.univasf.controller;

import br.edu.univasf.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static br.edu.univasf.Main.stage;

public class RelatorioReservaController implements Initializable {

    @FXML
    private TableView<?> tableRelatorio;  // Defina o tipo de dados correto para a tabela
    @FXML
    private Button carregarDadosButton;
    @FXML
    private Button gerarRelatorioButton;
    @FXML
    private Button voltarButton;
    @FXML
    private TextField campoCpf;  // Campo de texto para CPF

    // Simulando uma lista de clientes com pacotes turísticos
    private List<Cliente> listaClientes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Evento de clique do botão "Gerar Relatório"
        gerarRelatorioButton.setOnMouseClicked((MouseEvent event) -> {
            // Lógica para gerar o relatório, talvez exportando para um arquivo ou exibindo em uma nova tela
        });

        // Evento de clique do botão "Voltar"
        voltarButton.setOnMouseClicked((MouseEvent event) -> {
            Main.switchScreen("opcoes");
        });

        // Carregar dados de exemplo ao inicializar
        carregarDados();
    }

    // Método para carregar dados (simulação)
    private void carregarDados() {
        // Adicionando alguns clientes fictícios à lista
        listaClientes = List.of(
                new Cliente("João Silva", "123.456.789-00", "joao@exemplo.com", "(99) 99999-9999", "Pacote A", "Rio de Janeiro", 500.00, 30),
                new Cliente("Maria Oliveira", "987.654.321-00", "maria@exemplo.com", "(88) 88888-8888", "Pacote B", "São Paulo", 800.00, 50)
        );

        // Aqui você pode preencher a tabela com a lista de clientes
        // O código real dependerá do tipo de dados que você usa no TableView
    }

    // Método para buscar cliente por CPF
    public void buscarCliente(ActionEvent actionEvent) {
        String cpf = campoCpf.getText().trim();

        if (cpf.isEmpty()) {
            showAlert("Erro", "CPF não informado", "Por favor, informe um CPF para buscar.");
            return;
        }

        // Filtrando a lista de clientes pelo CPF
        Cliente clienteEncontrado = listaClientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (clienteEncontrado != null) {
            // Exibindo os dados do cliente na tabela
            preencherTabela(clienteEncontrado);
        } else {
            showAlert("Cliente não encontrado", "CPF não encontrado", "Nenhum cliente encontrado com esse CPF.");
        }
    }

    // Método para preencher a tabela com as informações do cliente
    private void preencherTabela(Cliente cliente) {
        // Aqui você vai configurar a tabela para exibir os dados do cliente e do pacote
        // Como exemplo, isso depende do tipo de dados da sua tabela, você precisará criar
        // os objetos correspondentes, como "Cliente" e "Pacote", para exibir corretamente.

        // Abaixo, simulo a adição de uma linha na tabela (é necessário ajustar o código para o seu modelo de dados)
        // Exemplo simples de como preencher a tabela:
        // tableRelatorio.getItems().add(cliente);
    }

    // Método para mostrar alertas de erro
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Classe Cliente simulada, substitua isso pelo seu modelo de dados real
    public static class Cliente {
        private String nome;
        private String cpf;
        private String email;
        private String telefone;
        private String nomePacote;
        private String destino;
        private double preco;
        private int vagasOcupadas;

        public Cliente(String nome, String cpf, String email, String telefone, String nomePacote, String destino, double preco, int vagasOcupadas) {
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
            this.telefone = telefone;
            this.nomePacote = nomePacote;
            this.destino = destino;
            this.preco = preco;
            this.vagasOcupadas = vagasOcupadas;
        }

        // Getters e setters

        public String getCpf() {
            return cpf;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public String getTelefone() {
            return telefone;
        }

        public String getNomePacote() {
            return nomePacote;
        }

        public String getDestino() {
            return destino;
        }

        public double getPreco() {
            return preco;
        }

        public int getVagasOcupadas() {
            return vagasOcupadas;
        }
    }
}
