# erp-toDotCom

API REST em Java (Spring Boot) para cadastro de clientes e produtos e registro de pedidos, com autenticação JWT.

![Diagrama do banco de dados](https://github.com/user-attachments/assets/da2b4997-18fd-46bc-a88e-5b720343d7a0)

## Stack

- Java 17, Spring Boot 3.2.5, Maven
- Spring Web, Spring Data JPA (Hibernate), Bean Validation, HATEOAS
- Spring Security com OAuth2 Resource Server (JWT assinado com par de chaves RSA)
- MySQL
- springdoc-openapi (Swagger UI), Lombok

## Documentação da API

- Swagger UI local: `http://localhost:8080/swagger-ui/index.html`
- Especificação OpenAPI local: `http://localhost:8080/v3/api-docs`
- Versão publicada: [SwaggerHub](https://app.swaggerhub.com/apis/DANIEL0333V/DanielApi/1.0)

## Modelo de dados

| Entidade | Tabela | Campos principais | Relacionamentos |
|---|---|---|---|
| `Cliente` | `cliente` | `id`, `nome`, `cpf` (validado com `@CPF`), `instanciado` | `N:1` com `User`; `1:N` com `Pedido` |
| `Produto` | `produto` | `id`, `descricao`, `preco`, `inserido` | `N:1` com `User` |
| `Pedido` | `pedido` | `id`, `data_pedido`, `total`, `status`, `efetuado` | `N:1` com `Cliente` e `User`; `1:N` com `ItemPedido` |
| `ItemPedido` | `item_pedido` | `id`, `quantidade` | `N:1` com `Pedido` e `Produto` |
| `User` | `tb_users` | `user_id` (UUID), `username` (único), `password` (BCrypt) | `N:N` com `Role` (`tb_user_roles`) |
| `Role` | `tb_roles` | `role_id`, `name` | — |

`StatusPedido`: `REALIZADO`, `CANCELADO`.

O esquema é gerado pelo Hibernate (`spring.jpa.hibernate.ddl-auto=update`).

## Endpoints

| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| POST | `/login` | Gera o token JWT (expira em 300 s) | público |
| POST | `/users` | Cria usuário | público |
| GET | `/users` | Lista usuários (paginado) | role `admin` |
| GET, POST | `/api/clientes` | Lista (filtro por exemplo) / cadastra cliente | autenticado |
| GET, PUT, DELETE | `/api/clientes/{id_client}` | Consulta / atualiza / remove cliente | autenticado |
| GET, POST | `/api/produtos` | Lista (filtro por exemplo) / cadastra produto | autenticado |
| GET, PUT, DELETE | `/api/produtos/{id_product}` | Consulta / atualiza / remove produto | autenticado |
| POST | `/api/pedidos` | Registra pedido com itens | autenticado |
| GET | `/api/pedidos/{id}` | Consulta pedido com itens | autenticado |
| PATCH | `/api/pedidos/{id}` | Atualiza o status do pedido | autenticado |

## Regras e validações

- Cliente: nome e CPF obrigatórios; CPF válido e sem duplicidade.
- Produto: descrição e preço obrigatórios.
- Pedido: cliente informado e existente, total obrigatório, lista de itens não vazia, produtos existentes. O pedido é criado com status `REALIZADO` e associado ao usuário do token.
- Erros tratados em `ApplicationControllerAdvice` (400 para regra de negócio e validação, 404 para pedido não encontrado). As mensagens ficam em `messages.properties`.

## Executando

Pré-requisitos: JDK 17 e MySQL.

```bash
export DB_USERNAME=root
export DB_PASSWORD=sua_senha
# opcional: DB_URL (padrão: jdbc:mysql://localhost:3306/erp_todotcom?createDatabaseIfNotExist=true)

./mvnw spring-boot:run
```

Na inicialização são criadas as roles `admin` e `basic` e um usuário `admin` (ver `AdminUserConfig`).

## Licença

MIT
