# TravelManager: Sistema de Gestão para Agências de Turismo

## Descrição do Projeto

**TravelManager** é um sistema de gestão projetado para otimizar e simplificar o funcionamento de uma agência de turismo. Este projeto visa permitir que os funcionários da agência possam gerenciar pacotes turísticos, cadastrar clientes, registrar reservas, controlar pagamentos, gerar itinerários e produzir relatórios detalhados. A principal missão do aplicativo é fornecer uma plataforma integrada e intuitiva para o gerenciamento eficiente de todas as operações da agência.

Este projeto foi desenvolvido como parte da disciplina de **Engenharia de Software 2** da **UNIVASF**.

---

## Funcionalidades

- **Gestão de Pacotes Turísticos**: Crie, edite e visualize pacotes turísticos detalhados com informações como destino, datas, itinerários e preços.
- **Cadastro de Clientes**: Armazene informações detalhadas dos clientes, como nome, CPF, endereço, telefone e histórico de reservas.
- **Reserva de Pacotes**: Realize a reserva de pacotes turísticos, associando clientes aos pacotes desejados com facilidade.
- **Gestão de Pagamentos**: Registre e controle os pagamentos das reservas, com a possibilidade de confirmar ou recusar transações.
- **Geração de Itinerários**: Crie itinerários personalizados para cada cliente, com informações completas sobre voos, passeios, acomodações, entre outros.
- **Geração de Relatórios**: Gere relatórios detalhados sobre clientes, reservas, pacotes e pagamentos, ajudando a tomar decisões baseadas em dados.

---

## Tecnologias Utilizadas

- **Java 23.0.1**: Utilizado como linguagem de programação para o desenvolvimento do sistema.
- **JavaFX 17.0.6**: Framework utilizado para criação da interface gráfica do usuário (GUI).
- **PostgreSQL 42.7.4**: Banco de dados relacional utilizado para armazenar as informações dos clientes, pacotes e reservas.
- **IntelliJ IDEA**: Ambiente de desenvolvimento integrado (IDE) utilizado para o desenvolvimento do código.

---

## Como Usar

### Pré-requisitos

1. **Clone o repositório** para seu ambiente local:

   ```bash
   git clone https://github.com/sansaocarrasco/TravelManager.git

2. **Configure o banco de dados PostgreSQL**
Crie as tabelas necessárias no seu banco de dados PostgreSQL (recomenda-se o uso do pgAdmin ou qualquer outra ferramenta de sua escolha).
Execute os seguintes comandos SQL para criar as tabelas cliente, pacote e reserva:

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY, -- Chave primária auto-incrementada
    cpf VARCHAR(11) UNIQUE NOT NULL, -- CPF deve ser único e não nulo
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    rua VARCHAR(255),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    numero VARCHAR(10),
    estado VARCHAR(2),
    telefone VARCHAR(15),
    ddd VARCHAR(3)
);

CREATE TABLE pacote (
    id SERIAL PRIMARY KEY, -- Chave primária auto-incrementada
    nome VARCHAR(255) NOT NULL,
    destino VARCHAR(255),
    datainicio DATE NOT NULL,
    datafim DATE NOT NULL,
    preco VARCHAR(20),
    num_vagas INT NOT NULL,
    hospedagem VARCHAR(255),
    itinerario TEXT,
    descricao TEXT,
    transporte BOOLEAN
);

CREATE TABLE reserva (
    reservaID SERIAL PRIMARY KEY,
    nomePacote VARCHAR(100) NOT NULL,
    pkfkPacoteID INTEGER,  -- ID do pacote (pode ser nulo)
    nomeCliente VARCHAR(100) NOT NULL,
    cpfCliente VARCHAR(11) UNIQUE,  -- CPF do cliente (único)
    datareserva DATE NOT NULL,
    CONSTRAINT fk_pacote FOREIGN KEY (pkfkPacoteID) REFERENCES pacote(id) ON DELETE CASCADE,
    CONSTRAINT fk_cliente FOREIGN KEY (cpfCliente) REFERENCES cliente(cpf) ON DELETE CASCADE,
    CONSTRAINT chk_reserva CHECK (pkfkPacoteID IS NOT NULL OR cpfCliente IS NOT NULL)
);

## Instruções para Execução
### Configuração do banco de dados:

Após a criação das tabelas, configure as credenciais de acesso ao banco de dados em seu arquivo de configuração, ou diretamente no código, conforme a estrutura que você utilizar.

### Rodar o aplicativo:

Abra o projeto no IntelliJ IDEA.
Compile e execute o projeto.
O aplicativo irá iniciar e você poderá acessar a interface gráfica para gerenciar as operações de pacotes turísticos, clientes, reservas, pagamentos, itinerários e relatórios.

### Funcionalidades:

Realize login como funcionário.
Cadastre clientes e pacotes turísticos.
Registre reservas de pacotes.
Acompanhe e gerencie os pagamentos.
Gere itinerários personalizados para os clientes.
Crie e visualize relatórios detalhados.

## Licença
Este projeto é licenciado sob a Licença MIT. Você pode usá-lo, modificá-lo e distribuí-lo conforme os termos dessa licença.
