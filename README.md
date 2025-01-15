# uol-host-full-stack

## Sobre

Cadastro de jogadores do UOL.

[Link do desavio](https://github.com/uolhost/test-backEnd-Java).

## Modelo - Diagrama De Classe

### Entidades

![class-diagram-entities](https://github.com/user-attachments/assets/5631ff00-59a3-40e3-8997-b6d6032fe4eb)

### Enumeração

![class-diagram-enums](https://github.com/user-attachments/assets/ec26ce65-b828-40a8-8365-2838273ddcf9)

# Fucionalidades

- Registra jogador.
- Listar jogadores.
- Deletar jogador pelo id.
- Editar jogador.

# Recurso Do Projeto 

## UOL Host Back End Spring Boot Java

- Java 17.
- Spring Boot.
- MVC.
- SOLID.
- Swagger.
- H2 SQL.
- JUnit 5.
- [API da liga da justiça](https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml).
- [API dos vingadores](https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json).
- Lombok.
- Validation.
- Ulid Creator.
- Jackson Dataformat XML.
- Web Flux.
- Hibernate/JPA.

# Requsições

## Jogador

### Registra Jogador

```json
POST
/api/players/register-player

{
  "name": "",
  "email": "",
  "telephone": "",
  "playerType": "AVENGERS"
}
```

### Listar Jogadores

```json
GET
/api/players/list-player?page=0&size=1&sort=id
```

### Deletar Jogador por id

```json
DELETE
/api/players/delete-player-by-id?id=1
```

### Editar Jogador

```json
PUT
/api/players/edit-player?id=1

{
  "name": "",
  "email": "",
  "telephone": "",
  "playerType": "AVENGERS"
}
```

# Execução Do Projeto

- Copie e execute repositório em uma IDE.
- Acesse [a docmentação da API](http://localhost:8080/swagger-ui/index.html) ou use outra plataforma para testa a API.

```bash
# clone repository
git clone https://github.com/abnerjosefelixbarbosa/challenge-back-end-hit.git
```

# Autor

Abner José Felix Barbosa

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/abner-jose-feliz-barbosa/)
