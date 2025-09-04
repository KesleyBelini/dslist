# DSList - API de Gerenciamento de Listas de Jogos

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=for-the-badge&logo=docker)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-85EA2D?style=for-the-badge&logo=swagger)

**API REST para gerenciamento de listas de jogos digitais**

## Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Como Executar](#como-executar)
- [Documentação da API](#documentação-da-api)
- [Modelo de Dados](#modelo-de-dados)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Arquitetura](#arquitetura)
- [Configuração](#configuração)
- [Testando a API](#testando-a-api)
- [Licença](#licença)
- [Autor](#autor)

## Sobre o Projeto

DSList é uma API REST desenvolvida em Spring Boot para gerenciamento de listas de jogos. Permite consultar informações de jogos, criar listas personalizadas e reorganizar a ordem dos itens.

### Funcionalidades

- **Gestão de Jogos**: Listagem e busca detalhada de jogos
- **Listas Personalizadas**: Criação e gestão de listas de jogos por categoria
- **Reordenação**: Sistema para reorganizar jogos nas listas
- **Documentação Interativa**: Interface Swagger para explorar a API
- **Docker**: Ambiente containerizado completo
- **Tratamento de Erros**: Respostas padronizadas para exceções

## Tecnologias

### Backend

- **Java 17**: Linguagem de programação
- **Spring Boot 3.5.4**: Framework principal
- **Spring Data JPA**: Persistência de dados
- **Spring Web**: APIs REST
- **Maven**: Gerenciamento de dependências

### Banco de Dados

- **PostgreSQL**: Banco principal (produção/desenvolvimento)
- **H2 Database**: Banco em memória (testes)

### Documentação

- **SpringDoc OpenAPI 3**: Documentação automática da API
- **Swagger UI**: Interface interativa para testes

### DevOps

- **Docker & Docker Compose**: Containerização
- **Spring Profiles**: Configuração por ambiente

## Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.6+
- Docker e Docker Compose (opcional)

### Execução Rápida

#### Docker (Recomendado)

```bash
# Clonar o repositório
git clone https://github.com/KesleyBelini/dslist.git
cd dslist

# Configurar variáveis de ambiente
cp .env.example .env
# Editar o arquivo .env com suas senhas

# Iniciar PostgreSQL + pgAdmin
docker-compose up -d

# Executar a aplicação
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Execução Local (Teste)

```bash
# Modo teste (H2 em memória)
./mvnw spring-boot:run

# Acessar: http://localhost:8080/swagger-ui/index.html
```

### Acessos Após Inicializar

- **Swagger UI**: <http://localhost:8080/swagger-ui/index.html>
- **H2 Console**: <http://localhost:8080/h2-console> (modo test)
- **pgAdmin**: <http://localhost:5050> (com Docker)

## Documentação da API

A API possui documentação completa e interativa através do Swagger/OpenAPI.

### Links

- **Swagger UI**: <http://localhost:8080/swagger-ui/index.html>
- **OpenAPI JSON**: <http://localhost:8080/v3/api-docs>

### Endpoints Principais

#### Jogos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/games` | Lista todos os jogos |
| `GET` | `/games/{id}` | Busca jogo por ID |

#### Listas de Jogos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/lists` | Lista todas as listas |
| `GET` | `/lists/{id}` | Busca lista por ID |
| `GET` | `/lists/{listId}/games` | Jogos de uma lista |
| `POST` | `/lists/{listId}/replacement` | Reordena jogos na lista |

## Modelo de Dados

![Modelo de Entidades](dslist-model.png)

## Estrutura do Projeto

```
src/main/java/com/devsuperior/dslist/
├── controllers/        # Controladores REST
├── services/          # Lógica de negócio
├── repositories/      # Acesso aos dados
├── entities/          # Entidades JPA
├── dto/              # Objetos de transferência
├── projections/      # Projeções JPA
├── exceptions/       # Tratamento de exceções
└── config/           # Configurações
```

## Arquitetura

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│ Controllers │ -> │  Services   │ -> │ Repositories│ -> │  Database   │
└─────────────┘    └─────────────┘    └─────────────┘    └─────────────┘
       │                   │                   │
       v                   v                   v
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│    DTOs     │    │  Entities   │    │ Projections │
└─────────────┘    └─────────────┘    └─────────────┘
```

## Configuração

### Perfis Disponíveis

#### test (Padrão)

- Banco H2 em memória
- Dados temporários para desenvolvimento rápido
- Console H2: <http://localhost:8080/h2-console>

#### dev (Desenvolvimento)

- PostgreSQL via Docker
- Dados persistentes
- Configuração em `application-dev.properties`

#### prod (Produção)

- PostgreSQL configurado via variáveis de ambiente
- Configuração em `application-prod.properties`

### Variáveis de Ambiente

#### Para Produção

```bash
DB_URL=jdbc:postgresql://localhost:5432/dslist
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

#### Para Docker

```bash
POSTGRES_PASSWORD=sua_senha_segura
PGADMIN_DEFAULT_PASSWORD=sua_senha_pgadmin
```

## Testando a API

### Exemplos de Requisições

```bash
# Listar todos os jogos
curl http://localhost:8080/games

# Buscar jogo específico
curl http://localhost:8080/games/1

# Listar todas as listas
curl http://localhost:8080/lists

# Jogos de uma lista específica
curl http://localhost:8080/lists/1/games

# Reordenar jogo (mover posição 2 para posição 0)
curl -X POST http://localhost:8080/lists/1/replacement \
  -H "Content-Type: application/json" \
  -d '{"sourceIndex": 2, "destinationIndex": 0}'
```

### Exemplo de Resposta

#### GET /games

```json
[
  {
    "id": 1,
    "title": "Mass Effect Trilogy",
    "year": 2012,
    "imgUrl": "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png",
    "shortDescription": "Lorem ipsum dolor sit amet..."
  }
]
```

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

## Autor

**Kesley Belini**

[![GitHub](https://img.shields.io/badge/GitHub-@KesleyBelini-181717?style=for-the-badge&logo=github)](https://github.com/KesleyBelini)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Kesley%20Belini-0A66C2?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/kesleybelini)

**Se este projeto te ajudou, considere dar uma estrela no repositório!**
