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

### API : Fonctionnement & Issues

*Sauf Api Auth toutes les routes son protégées par un token qu'il faut passer avec un Header de la forme : 'Authorization: <token<token>>'*

#### Auth

- POST - /api/users - Créer un nouveau utilisateur

- POST - /api/users/login - GET /api/users/validate

- DELETE - /api/users/{id} - Supprimer
1) Ajouter securitée : un utilisateur ne peux se supprimer que lui même, Actuellement n'importe qui peut supprimer un utilisateur s'il connait sont ID

#### Invocation

- POST - /api/invocations - Invoque un monstre
0) Choisit un monstre dans la liste des ressources (Json)
1) Appelle Monstre Api (corriger mapper MonstreResource -> MonstreDto : voir parametre par défaut Mapper.RessourceMapper)
2) Appelle Joueur Api pour ajouter l'id du monstre à la liste de monstre du joueur
3) Save Invocation
4) en cas de probleme (api injoignable) Save backup (au lieu de Save Invocation)

- POST - /api/invocations/recup - Rejoue les invocations stoppées
0) Recupere la liste de backup du joueur
1) Si l'id Monstre est null -> Appelle Monstre Api, Joueur Api
2) Sinon appelle directement Joueur Api
3) Suppprime backup
4) Save Invocation

- Test Unitaire Utils.CalculInvocationTest
Testé sur 10000 lancé avec une marge d'erreur de 1.5%

#### Joueur

- GET - /api/joueurs/{id} - Récupérer un joueur par ID

- PUT - /api/joueurs/{id} - Mettre à jour un joueur

- DELETE - /api/joueurs/{id} - Supprimer un joueur

- GET - /api/joueurs - Récupérer tous les joueurs

- POST - /api/joueurs - Créer un nouveau joueur

- POST - /api/joueurs/{id}/levelup - Monter de niveau

- POST - /api/joueurs/{id}/experience - Gagner de l'expérience

- GET - /api/joueurs/monsters - Récupérer la liste d'ID de monstres

- POST - /api/joueurs/monsters - Acquérir un nouveau monstre
1) Sécurité : Ajouter sécuritée pour qu'un joueur ne puisse pas ajouter n'importe quel monstre à sa liste si il connait sont ID.

- GET - /api/joueurs/level/{id} - Récupérer le niveau d'un joueur

- DELETE - /api/joueurs/{id}/monsters - Supprimer un monstre
1) Sécurité : Ajouter sécuritée (pareil que pour  *Acquérir un nouveau monstre*)

#### Monstre

- GET - /api/monstres/{id} - Get By Id

- PUT - /api/monstres/{id} - Update

- DELETE - /api/monstres/{id} - Delete

- GET - /api/monstres - Get All Monstres

- POST - /api/monstres - Create
1) Sécurité : Ajoutée sécuritée pour que la création passe uniquement par l'api invocation

- DELETE - /api/monstres - Delete All

