# uol-host-full-stack

## Sobre

Full stack da aplicação uol host  

## Modelo

![uol-host-diagram-class drawio](https://github.com/user-attachments/assets/004feb85-63bb-4037-9d57-258d63975aa5)

# Recurso Do Projeto 

## UOL Host Back End Java

- Java 17.
- Spring Boot.
- MVC.
- SOLID.
- Swegger.
- H2 SQL.
- JUnit 5.
- [API da liga da justiça](https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml).
- [API dos vingadores](https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json).
- Lombok.
- Validation.
- Ulid Creator.
- Jackson Dataformat XML.
- Web Flux.

# Requsições

## Player

### Registra Jogador

```json
/api/players/register-player

{
  "name": "",
  "email": "",
  "telephone": "",
  "playerType": "AVENGERS"
}
```

### Listar Jogador

```json
/api/players/list-player?page=0&size=1&sort=id
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
