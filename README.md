# TD Docker / Spring Boot / MongoDB

## Endpoints

- API Endpoint: `http://localhost:8080/api/produits`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Docker

To run the project, you need to have Docker installed on your machine.

```shell
cd ./docker-dev-env
docker-compose up -d --build
```
## Spring Boot

### Création d'un projet Spring Boot

Goto [Spring Intializr](https://start.spring.io/)

### Java

- Package `imt.production.dev`

Main classes

- DevApplication.java
- Controller/ProduitController.java
- Service/ProduitService.java
- RepositoryProduitRepository.java
- Model/Produit.java

### Add Swagger

Modify some files

- `pom.xml`

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

- `ProduitController.java`

```java
@Tag(name = "Produits", description = "Gestion des produits")
@Operation(summary = "Récupérer tous les produits")
@Operation(summary = "Créer un nouveau produit")
```



## MongoDB

- db: dev
- collection: produits

## Curl Test

- Insert data

```shell
bash insert_some_data.sh
```

- Get all

```shell
curl http://localhost:8080/api/produits
```

- Get id 1

```shell
curl http://localhost:8080/api/produits/1
```

- Create

```shell
curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/produits -d '{"id": "1", "nom": "Produit Test", "description": "Description du produit", "prix": 100.0}'
```

- Update id 1

```shell
curl -X PUT -H "Content-Type: application/json" http://localhost:8080/api/produits/1 -d '{"nom": "Produit Plus Test", "description": "Description du produit", "prix": 100.0}'
```

- Delete id 1

```shell
curl -X DELETE http://localhost:8080/api/produits/1
```

## Issues 

- Improve Exception Management

