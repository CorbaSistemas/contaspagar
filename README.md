# contaspagar
**Sistema de Contas a Pagar**
Este projeto é uma API REST para um sistema simples de contas a pagar. O sistema permite realizar o CRUD de contas a pagar, alterar a situação delas quando for efetuado pagamento, obter informações sobre as contas cadastradas no banco de dados e importar um lote de contas de um arquivo CSV. Também inclui um CRUD de usuário com autenticação via JWT.

**Tecnologias Utilizadas**
  **Java 17:** Linguagem de programação utilizada para desenvolver a aplicação.
  **Spring Boot:** Framework que facilita a configuração e o desenvolvimento de aplicações Java.
  **PostgreSQL:** Banco de dados relacional utilizado para armazenar as informações das contas e dos usuários.
  **Docker:** Ferramenta para criar e gerenciar containers.
  **Docker Compose:** Ferramenta para orquestrar múltiplos containers Docker.
  **JWT (JSON Web Token):** Método de autenticação utilizado para proteger as APIs.
  **Domain Driven Design (DDD):** Abordagem de design utilizada para organizar o projeto.
  **Flyway:** Ferramenta para versionamento e migração de banco de dados.
  **JPA (Java Persistence API):** API de persistência utilizada para mapear objetos Java para o banco de dados relacional.

**Pré-requisitos**
  Antes de começar, você precisará ter o seguinte instalado em sua máquina:
  Java 17
  Docker
  Docker Compose


**Configuração do Projeto**
  **Clone o repositório:**
    git clone https://github.com/corbasistemas/contaspagar.git
    cd contaspagar

**Configure o banco de dados PostgreSQL no arquivo application.yml:**
spring:
  datasource:
    url: jdbc:postgresql://db:5432/contasdb
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

flyway:
  enabled: true
  baseline-on-migrate: true

**Construa e execute a aplicação usando Docker Compose:**
docker-compose up --build

**Estrutura do Projeto**
Domain: Contém as entidades e serviços de domínio.
Infrastructure: Contém os repositórios e outras implementações de infraestrutura.
Service: Contém os casos de uso da aplicação.
Controller: Contém os controladores REST que expõem as APIs.
Config: Configuração do Swagger.
Security: Controle do JWT.

**APIs**

**Contas a Pagar**
  **Cadastrar Conta**
  POST /api/contas

  **Atualizar Conta**
  PUT /api/contas/{id}
  
  **Alterar Situação da Conta**
  PATCH /api/contas/{id}/situacao
  
  **Obter Lista de Contas**
  GET /api/contas?startDate={startDate}&endDate={endDate}&descricao={descricao}&page={page}&size={size}
  
  **Obter Conta por ID**
  GET /api/contas/{id}
  
  **Obter Valor Total Pago por Período**
  GET /api/contas/total-pago?startDate={startDate}&endDate={endDate}
  
  **Importar Arquivo CSV**
  POST /api/contas/import/{file}


**Usuários**
  **Cadastrar Usuário**
  POST /api/usuarios
  
  **Obter Usuário por ID**
  GET /api/usuarios/{username}


**Autenticação - Auth**
  **Login**
  POST /api/auth/login


