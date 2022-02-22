# TIMWI Coding Challenge

Développez les fonctionnalités suivantes dans la technologie web cible en utilisant l’open API de Spotify. Votre temps est précieux, fixez vous un temps limite à y consacrer. Vous ne serez pas jugés sur la complétude du périmètre et vous aurez toujours l’occasion d’expliquer lors du débriefing comment vous auriez poursuivi, terminé, voire refactoré votre projet !

## Pour commencer

### Pré-requis

- Java 11
- Node/Npm
- Docker

### Installation

*[Si vous disposez de docker-compose vous pouvez passer au démarrage](#Démarrage)*

Tout d'abord vous devez vous placer dans le dossier racine du projet

```shell
$ cd timwi-coding-challenge
```

Ensuite vous devez installer les dépendances maven du projet spring boot

```shell
$ ./mvnw clean install -DskipTests
```

Ensuite vous devez installer les dépendances npm du projet Vue.js

```shell
$ cd frontend
```

```shell
$ npm install
```

### Génération d'un token

Vous devez générer un [token](https://developer.spotify.com/console/get-search-item/). Il faudra ensuite l'exporter comme variable d'environnement.

```shell
$ EXPORT TOKEN=YOUR_TOKEN_HERE
```

## Démarrage

### Docker-compose

On vient modifier la valeur de la variable *TOKEN* dans le fichier *start.sh* puis : 

```shell
$ chmod +x start.sh
```

```shell
$ ./start.sh
```

### Manuel

Tout d'abord vous devez lancer la base de données :

```shell
$ docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
```

Ensuite on vient créer nos variables d'environnement avant de lancer le backend et on renseigne le token :

```shell
$ export DB_HOST=localhost; export DB_PORT=5432; export DB_NAME=postgres; export DB_USERNAME=postgres; export DB_PASSWORD=password; export SPOTIFY_TOKEN=YOUR_TOKEN_HERE
 ```

Ensuite vous devez lancer le backend en étant à la racine du projet :

```shell
$ ./mvnw spring-boot:run
 ```

Puis vous pouvez lancer le frontend en étant dans le dossier frontend :

```shell
$ npm serve
```

## Fabriqué avec

* [Spring](https://spring.io/) - Framework Java
* [Vue.js](https://vuejs.org/) - Framework Javascript
* [PostgreSQL](https://www.postgresql.org/) - Base de données relationnelle

## Auteurs

* **Maël DONNART** _alias_ [@maeldonn](https://github.com/maeldonn)
