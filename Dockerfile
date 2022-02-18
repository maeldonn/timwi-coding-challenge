FROM maven:3.8.4-eclipse-temurin-11 as maven

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn package -DskipTests

FROM adoptopenjdk/openjdk11

WORKDIR /spotify-challenge

COPY --from=maven target/spotify-challenge-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "./spotify-challenge-0.0.1-SNAPSHOT.jar"]