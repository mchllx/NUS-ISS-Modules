FROM node:21 AS ng-builder

RUN npm i -g @angular/cli

WORKDIR /ngapp

COPY docker/package*.json .
COPY docker/angular.json .
COPY docker/tsconfig.* .
COPY docker/server.ts .
COPY docker/src src

RUN npm ci
RUN ng build
# /ngapp/dist/docker/browser

# start on this linux server
FROM maven:3-eclipse-temurin-21 AS sb-builder

WORKDIR /sbapp

COPY demo/mvnw .
COPY demo/mvnw.cmd .
COPY demo/pom.xml .
COPY demo/.mvn .mvn
COPY demo/src src
COPY --from=ng-builder /ngapp/dist/docker/browser/ /sbapp/src/main/resources/static

RUN mvn package -e -Dmaven.test.skip=true

FROM maven:3-eclipse-temurin-21
WORKDIR /app

COPY --from=sb-builder /sbapp/target/demo-0.0.1-SNAPSHOT.jar app.jar

# ENV SPRING_DATA_MONGODB_URI=mongodb://localhost:37928/boardgames

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar ./app.jar
