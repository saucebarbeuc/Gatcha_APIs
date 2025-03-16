# TD Docker / Spring Boot / MongoDB

Projet réalisé par Caron Florimond, Beaurepaire Paul et Watel Noa

Gatche_api est un projet de démonstration pour l'utilisation de Docker, Spring Boot et MongoDB.
Il a pour but de simuler un jeu de type "Gatcha" où l'utilisateur peut invoquer des monstres et les ajouter à sa collection.

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
1) Choisit un monstre dans la liste des ressources (JSON)
2) Appelle l'API Monstre pour obtenir les détails du monstre
3) Appelle l'API Joueur pour ajouter l'ID du monstre à la liste des monstres du joueur
4) Sauvegarde l'invocation
5) En cas de problème (API injoignable), sauvegarde une copie de secours (backup)

- POST - /api/invocations/recup - Rejoue les invocations stoppées
1) Récupère la liste de secours du joueur
2) Si l'ID du monstre est null, appelle les API Monstre et Joueur
3) Sinon, appelle directement l'API Joueur
4) Supprime la copie de secours
5) Sauvegarde l'invocation

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
Sécurité : Ajouter sécuritée pour qu'un joueur ne puisse pas ajouter n'importe quel monstre à sa liste si il connait sont ID.

- GET - /api/joueurs/level/{id} - Récupérer le niveau d'un joueur

- DELETE - /api/joueurs/{id}/monsters - Supprimer un monstre
Sécurité : Ajouter sécuritée (pareil que pour  *Acquérir un nouveau monstre*)

#### Monstre

- GET - /api/monstres/{id} - Get By Id

- PUT - /api/monstres/{id} - Update

- DELETE - /api/monstres/{id} - Delete

- GET - /api/monstres - Get All Monstres

- POST - /api/monstres - Create
Sécurité : Ajoutée sécuritée pour que la création passe uniquement par l'api invocation

- DELETE - /api/monstres - Delete All

## Front
 
Plusieurs front sont disponibles sur les routes : 'http://localhost:8081/index.html' et 'http://localhost:8084/index.html'. Elles permettent :

Pour 'http://localhost:8081/index.html' :

- de créer un compte utilisateur
- de se connecter à un compte utilisateur en ayant un token

Pour 'http://localhost:8084/index.html' :
- d'invoquer un monstre

Pour que les fronts fonctionnent correctement, il est nécessaire de lancer les containers.
Pour pouvoir invoquer un monstre dans le front Invocation, il faut au préalable copier le Token utilisateur généré lors de la connexion dans le front Authentification.

