### STAGE 1: Build ###
FROM maven:3.8.4-eclipse-temurin-11 AS build

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn package -DskipTests

### STAGE 2: Run ###
FROM adoptopenjdk/openjdk11

WORKDIR /spotify-challenge

COPY --from=build target/spotify-challenge-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "./spotify-challenge-0.0.1-SNAPSHOT.jar"]