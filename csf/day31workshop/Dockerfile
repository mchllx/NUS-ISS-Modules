FROM node:21

RUN npm i -g @angular/cli

WORKDIR /app

COPY package.* .
COPY angular.json .
COPY tsconfig.* .
COPY src .

RUN npm ci && ng builder

# start on this linux server
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

ENV SPRING_DATA_MONGODB_URI=mongodb://localhost:37928/boardgames

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar ./app.jar