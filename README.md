# TD Docker / Spring Boot / MongoDB

## Endpoints

- API Endpoint: `http://localhost:8081/api/users`
- Auth Swagger: `http://localhost:8081/swagger-ui.html`

- API Endpoint: `http://localhost:8082/api/joueurs`
- Auth Swagger: `http://localhost:8082/swagger-ui.html`

- API Endpoint: `http://localhost:8083/api/monstres`
- Auth Swagger: `http://localhost:8083/swagger-ui.html`

- API Endpoint: `http://localhost:8084/api/invocations`
- Auth Swagger: `http://localhost:8084/swagger-ui.html`

## Docker

To run the project, you need to have Docker installed on your machine.

#### Run

```shell
docker-compose down && docker-compose up -d --build
```

#### Clean docker install

```shell
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker image rm $(docker image ls -a -q)
docker volume rm $(docker volume ls -q)
```

## Spring Boot

### Création d'un projet Spring Boot

Goto [Spring Intializr](https://start.spring.io/)

### API

- Auth

- Joueur

- Monstre



