# API de Agendamento

Esta é uma API REST para gerenciamento de agendamentos.

## Requisitos

- Java 17

## Dependências

- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [Project Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok) (opcional)
- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)

## Configuração do Banco de Dados MySQL com Docker

### Pré-requisitos

Certifique-se de ter o Docker instalado em seu sistema. Você pode baixar e instalar o Docker a partir do [site oficial](https://www.docker.com/get-started).

### Instalação do banco de dados

Abra um terminal e execute o seguinte comando para iniciar os contêineres do MySQL e do phpMyAdmin:

```bash
docker-compose up -d
```

### Configuração JDBC

Para estabelecer a conexão JDBC com o banco de dados MySQL em sua aplicação Java, utilize as seguintes informações de configuração:

- URL JDBC: `jdbc:mysql://localhost:3307/agendamento_med`
- Nome de usuário: `agendamento_med`
- Senha: `agendamento_med`

## Instalação

Execute o aplicativo usando alguma IDE com suporte ao Spring Boot ou use o seguinte comando:

```bash
mvn spring-boot:run
```

## Uso

A API pode ser acessada através do seguinte endpoint: [http://localhost:8080](http://localhost:8080)

## Contribuição

Contribuições são sempre bem-vindas! Sinta-se à vontade para abrir um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).
