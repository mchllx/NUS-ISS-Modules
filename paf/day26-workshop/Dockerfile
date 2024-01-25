FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /src

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN mvn package -e -Dmaven.test.skip=true

FROM maven:3-eclipse-temurin-21
WORKDIR /app

COPY --from=builder /src/target/day26-workshop-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATA_MONGODB_URI=mongodb://mongo:G2fCe3gg4BDfA6GhHHBC4HBaH3Bga2H2@viaduct.proxy.rlwy.net:37928

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar ./app.jar