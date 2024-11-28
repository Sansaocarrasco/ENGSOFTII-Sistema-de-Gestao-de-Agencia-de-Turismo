
<h1>TravelManager: Sistema de Gestão para Agências de Turismo</h1>
<p align="center">
  <img src="https://github.com/user-attachments/assets/b109dd9f-43b9-49ff-9133-cce5297eb1d4" alt="TravelManager"/>
</p>

## Descrição do Projeto

**TravelManager** é um sistema de gestão projetado para otimizar e simplificar o funcionamento de uma agência de turismo. Este projeto visa permitir que os funcionários da agência possam gerenciar pacotes turísticos, cadastrar clientes, registrar reservas, controlar pagamentos, gerar itinerários e produzir relatórios detalhados. A principal missão do aplicativo é fornecer uma plataforma integrada e intuitiva para o gerenciamento eficiente de todas as operações da agência.

Este projeto foi desenvolvido como parte da disciplina de **Engenharia de Software 2** da **UNIVASF**.

---

## Funcionalidades

- **Fazer Login**: Um funcionário devidamente cadastrado no Travel Manager poderá desfrutar de todas as funcionalidades do sistema.
- **Gestão de Pacotes Turísticos**: Crie, edite e visualize pacotes turísticos detalhados com informações como destino, datas, itinerários e preços.
- **Cadastro de Clientes**: Armazene informações detalhadas dos clientes, como nome, CPF, endereço, telefone e histórico de reservas.
- **Reserva de Pacotes**: Realize a reserva de pacotes turísticos, associando clientes aos pacotes desejados com facilidade.
- **Gestão de Pagamentos**: Registre e controle os pagamentos das reservas, com a possibilidade de confirmar ou recusar transações.
- **Geração de Itinerários**: Crie itinerários personalizados para cada cliente, com informações completas sobre voos, passeios, acomodações, entre outros.
- **Geração de Relatórios**: Gere relatórios detalhados sobre clientes, reservas, pacotes e pagamentos, ajudando a tomar decisões baseadas em dados.

---

## Tecnologias Utilizadas

- **Java --versão JDK 23.0.1**: Utilizado como linguagem de programação para o desenvolvimento do sistema.
- **JavaFX --versão SDK 23.0.1**: Framework utilizado para criação da interface gráfica do usuário (GUI).
- **PostgreSQL --versão JDBC 42.7.4**: Banco de dados relacional utilizado para armazenar as informações dos clientes, pacotes e reservas.
- **IntelliJ IDEA --versão 2024.2.4**: Ambiente de desenvolvimento integrado (IDE) utilizado para o desenvolvimento do código.

---

## Como Usar

### Pré-requisitos

1. **Clone o repositório** para seu ambiente local:

   ```bash
   https://github.com/Sansaocarrasco/ENGSOFTII-Sistema-de-Gestao-de-Agencia-de-Turismo.git

2. **Configuração do banco de dados PostgreSQL**
O programa faz a criação de todo o banco de dados automaticamente, é necessário apenas configurar a classe ConnectionFactory, localizada em utils.

private static final String URL = "jdbc:postgresql://localhost:5432/bdteste";
private static final String USER = "joaopedro";
private static final String PASSWORD = "123";

substitua esses campos de acordo com suas credenciais de seu banco de dados, recomendamos utilizar o mesmo banco de dados, o PgAdmin 4.

3. **Configuração das dependências necessarias**

## Baixando as dependências

### IDE (Intellij)
Recomendamos utilizar a IDE Intellij para uma correta instalação de suas dependências, mas normalmente qualquer IDE deverá funcionar para o projeto.
Ele pode ser encontrado no link: https://www.jetbrains.com/idea/download/?section=windows
Para o referente projeto utilizamos o IntelliJ IDEA versão 2024.2.4

### Banco de dados (PgAdmin 4)
Caso esteja utilizando o mesmo banco de dados PgAdmin 4 é necessário ter o modulo jdbc postgresql mais atualizado.
Ele pode ser encontrado no link: https://jdbc.postgresql.org/download/
Para o referente projeto utilizamos o jdbc com a versão 42.7.4

### Visualização de Telas (JafaFX)
Para as telas, é necessario ter o modulo sdk mais atualizado do javafx.
Ele pode ser encontrado no link: https://gluonhq.com/products/javafx/
Para o referente projeto utilizamos o sdk com a versão 23.0.1

## Adicionando as dependencias ao projeto

Ao abrir o Intellij clique em "File" e depois vá em "Project Structure" depois "Modules"
![image](https://github.com/user-attachments/assets/8ba629a0-ec3c-42dd-bf14-ea83bcd19707)
Aperte no "+" e adicione as dependencias do Banco de Dados e do JavaFX
![image](https://github.com/user-attachments/assets/dee51ea2-6866-48c3-89f3-924301e68647)
clique em "Apply" e depois em "Ok", feche a tela e execute o programa

## Instruções para Execução
### Rodar o aplicativo:

Com o projeto aberto, encontre a classe "Main" do projeto e a execute.
O aplicativo irá iniciar e você será redirecionado a tela de Login do Travel Manager.
![image](https://github.com/user-attachments/assets/8ce43569-f2f8-4f87-8786-f155349d9396)

Forneça as seguintes credenciais:
Usuário: admin
Senha: 1234

Clique em entrar.

Após isso você poderá fazer as operações de adicionar cliente, adicionar pacotes, reservar um pacote, gerar relatórios das reservas e muitas outras funcionalidades.


## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](./LICENSE) para mais detalhes.
