# 🎮 DSList - Game Lists Management API

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-green?style=flat-square&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=flat-square&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=flat-square&logo=docker)

**DSList** é uma API REST moderna para gerenciamento de listas de jogos digitais, permitindo consultar informações de jogos, criar listas personalizadas e reorganizar a ordem dos itens.

## ✨ Features

- 🎮 **CRUD de Jogos**: Consulta detalhada e listagem de jogos
- 📋 **Gerenciamento de Listas**: Criar e gerenciar listas de jogos  
- 🔄 **Reordenação**: Reorganizar posição dos jogos nas listas
- 📚 **Swagger UI**: Documentação interativa da API
- 🐳 **Docker**: Ambiente containerizado pronto para uso
- 🔒 **Segurança**: CORS configurado e tratamento de exceções

## 🚀 Quick Start

### Docker (Recomendado)

```bash
# Clone o repositório
git clone https://github.com/KesleyBelini/dslist.git
cd dslist

# Configure variáveis de ambiente
cp .env.example .env
# Edite .env com suas senhas

# Inicie PostgreSQL + pgAdmin
docker-compose up -d

# Execute a aplicação
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Execução Local

```bash
# Modo teste (H2 em memória)
./mvnw spring-boot:run

# Acesse: http://localhost:8080/swagger-ui.html
```

## 📖 Documentação

- **Swagger UI**: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
- **H2 Console**: [`http://localhost:8080/h2-console`](http://localhost:8080/h2-console) (modo test)
- **pgAdmin**: [`http://localhost:5050`](http://localhost:5050) (com Docker)

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3.5.4, Spring Data JPA
- **Database**: PostgreSQL (prod/dev), H2 (test)
- **Documentation**: Swagger/OpenAPI 3
- **DevOps**: Docker, Docker Compose, Maven

## 📋 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/games` | Lista todos os jogos (resumo) |
| `GET` | `/games/{id}` | Detalhes de um jogo |
| `GET` | `/lists` | Lista todas as listas |
| `GET` | `/lists/{id}` | Detalhes de uma lista |
| `GET` | `/lists/{id}/games` | Jogos de uma lista |
| `POST` | `/lists/{id}/replacement` | Reordena jogos na lista |

## 🏗️ Architecture

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

## 🔧 Configuration

### Profiles

- **test** (default): H2 in-memory database
- **dev**: PostgreSQL local
- **prod**: PostgreSQL with environment variables

### Environment Variables

```bash
# Database
DB_URL=jdbc:postgresql://localhost:5432/dslist
DB_USERNAME=postgres
DB_PASSWORD=your_password

# CORS
CORS_ORIGINS=http://localhost:3000,http://localhost:5173
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Kesley Belini**

- GitHub: [@KesleyBelini](https://github.com/KesleyBelini)
- LinkedIn: [Kesley Belini](https://linkedin.com/in/kesleybelini)
