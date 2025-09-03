# DSList - API de Gerenciamento de Listas de Jogos

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue)](https://www.postgresql.org/)
[![H2 Database](https://img.shields.io/badge/H2-Database-lightblue)](https://www.h2database.com/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/API-Swagger-85EA2D)](https://swagger.io/)

## 📋 Sobre o Projeto

DSList é uma API REST desenvolvida em Spring Boot para gerenciamento de listas de jogos. O projeto permite criar, organizar e gerenciar coleções de jogos de forma eficiente, oferecendo funcionalidades para listagem, busca detalhada e reordenação de jogos em listas personalizadas.

### 🎯 Funcionalidades Principais

- **Gestão de Jogos**: Listagem completa e busca detalhada de jogos
- **Listas Personalizadas**: Criação e gestão de listas de jogos por categoria
- **Reordenação**: Sistema de drag-and-drop para reordenar jogos nas listas
- **API Documentada**: Documentação interativa com Swagger/OpenAPI
- **Multi-ambiente**: Configuração para desenvolvimento, teste e produção

## 🚀 Tecnologias Utilizadas

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
- **Profiles Spring**: Configuração multi-ambiente

## 📚 Documentação da API

A API está totalmente documentada com Swagger/OpenAPI. Após inicializar a aplicação, acesse:

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### 🎮 Endpoints Principais

#### Games

- `GET /games` - Lista todos os jogos
- `GET /games/{id}` - Busca jogo por ID

#### Game Lists

- `GET /lists` - Lista todas as listas de jogos
- `GET /lists/{id}` - Busca lista por ID
- `GET /lists/{listId}/games` - Lista jogos de uma lista específica
- `POST /lists/{listId}/replacement` - Reordena jogos na lista

## 🛠️ Instalação e Execução

### Pré-requisitos

- Java 17+
- Maven 3.6+
- Docker e Docker Compose (opcional)

### 1. Clone o Repositório

```bash
git clone https://github.com/KesleyBelini/dslist.git
cd dslist
```

### 2. Execução Local (Perfil de Teste)

```bash
# Compilar o projeto
mvn clean compile

# Executar com H2 (banco em memória)
mvn spring-boot:run
```

### 3. Execução com Docker (Perfil de Desenvolvimento)

```bash
# Subir PostgreSQL
docker-compose up -d

# Executar aplicação no perfil dev
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 4. Execução em Produção

```bash
# Definir variáveis de ambiente
export DB_URL=jdbc:postgresql://localhost:5432/dslist
export DB_USERNAME=postgres
export DB_PASSWORD=1234567

# Executar no perfil de produção
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## 🔧 Configuração

### Profiles Disponíveis

#### Test (Padrão)

- Banco H2 em memória
- Console H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`

#### Development

- PostgreSQL via Docker
- Configuração em `application-dev.properties`

#### Production

- PostgreSQL configurado via variáveis de ambiente
- Otimizações para produção

### Variáveis de Ambiente (Produção)

```bash
DB_URL=jdbc:postgresql://localhost:5432/dslist
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

## 🏗️ Arquitetura do Projeto

```
src/
├── main/java/com/devsuperior/dslist/
│   ├── config/           # Configurações (CORS, OpenAPI)
│   ├── controllers/      # Controllers REST
│   ├── dto/             # Data Transfer Objects
│   ├── entities/        # Entidades JPA
│   ├── exceptions/      # Tratamento de exceções
│   ├── projections/     # Projeções JPA
│   ├── repositories/    # Repositórios JPA
│   └── services/        # Lógica de negócio
└── resources/
    ├── application*.properties  # Configurações por ambiente
    └── import.sql              # Dados de teste
```

### Modelo de Dados

#### Game

- ID, título, ano, gênero, plataformas
- Pontuação, URLs de imagem
- Descrições curta e longa

#### GameList

- ID e nome da lista

#### Belonging (Relacionamento)

- Posição do jogo na lista
- Chaves estrangeiras para Game e GameList

## 🚦 Testes

### Testar Endpoints

```bash
# Listar jogos
curl http://localhost:8080/games

# Buscar jogo específico
curl http://localhost:8080/games/1

# Listar todas as listas
curl http://localhost:8080/lists

# Jogos de uma lista específica
curl http://localhost:8080/lists/1/games
```

## 🎨 Recursos Técnicos Implementados

### Segurança

- **CORS configurado** para requisições cross-origin
- **Tratamento global de exceções** com respostas padronizadas
- **Validação de entrada** nos endpoints

### Performance

- **Projeções JPA** para consultas otimizadas
- **DTOs específicos** para cada contexto
- **Lazy loading** configurado adequadamente

### Boas Práticas

- **Separation of Concerns** (Controllers, Services, Repositories)
- **Dependency Injection** com constructor injection
- **Profile-based configuration** para diferentes ambientes
- **API documentation** com OpenAPI/Swagger

## 📈 Melhorias Futuras

- [ ] Autenticação e autorização (Spring Security)
- [ ] Cache com Redis
- [ ] Testes unitários e de integração
- [ ] Pagination nos endpoints de listagem
- [ ] Upload de imagens para jogos
- [ ] API para criação/edição de jogos e listas

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👤 Autor

**Kesley Belini**

- GitHub: [@KesleyBelini](https://github.com/KesleyBelini)
- LinkedIn: [Kesley Belini](https://linkedin.com/in/kesleybelini)

---

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório!
