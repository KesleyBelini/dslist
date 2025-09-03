# Instruções para executar o projeto com Docker

## Pré-requisitos
- Docker
- Docker Compose
- Java 17+
- Maven

## Como executar

### 1. Subir os serviços (PostgreSQL + pgAdmin)
```bash
docker-compose up -d
```

### 2. Verificar se os containers estão rodando
```bash
docker ps
```

### 3. Executar a aplicação Spring Boot
```bash
# Com profile de desenvolvimento (PostgreSQL)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Ou com profile de teste (H2)
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

## Serviços disponíveis

### PostgreSQL
- **Host**: localhost
- **Porta**: 5432
- **Database**: dslist
- **Usuário**: postgres
- **Senha**: dslist123

### pgAdmin (Interface gráfica do PostgreSQL)
- **URL**: http://localhost:5050
- **Email**: admin@dslist.com
- **Senha**: dslist123

#### Configurando conexão no pgAdmin:
1. Acesse http://localhost:5050
2. Faça login com as credenciais acima
3. Crie uma nova conexão:
   - **Host**: postgresql (nome do container)
   - **Port**: 5432
   - **Database**: dslist
   - **Username**: postgres
   - **Password**: dslist123

## Comandos úteis

### Parar os serviços
```bash
docker-compose down
```

### Parar e remover volumes (CUIDADO: apaga os dados)
```bash
docker-compose down -v
```

### Ver logs dos containers
```bash
docker-compose logs -f
```

### Executar apenas PostgreSQL
```bash
docker-compose up -d postgresql
```

## Profiles Spring Boot

- **test**: Usa banco H2 em memória (para testes)
- **dev**: Usa PostgreSQL via Docker
- **prod**: Para produção (configurar separadamente)
