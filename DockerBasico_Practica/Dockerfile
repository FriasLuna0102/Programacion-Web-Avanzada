# Primera etapa: Compilacion con Gradle
FROM gradle:8.4-jdk21 AS build

WORKDIR /app

COPY . /app

RUN chmod +x gradlew


RUN ./gradlew build

# Segunda etapa: Imagen final
# No utilizo esta imagen alpine que es menor que la de slim debido a que necesito de java 21.
#FROM openjdk:17-jdk-alpine

#Lo tengo aqui en caso de que requiera de java 21. Ya que alpine no tiene imagen con java 21.
FROM openjdk:21-jdk-slim

WORKDIR /app

RUN mkdir /app/logs

COPY --from=build /app/build/libs/practica-2-0.0.1-SNAPSHOT.jar my-app.jar

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

VOLUME /app/logs

ENTRYPOINT ["sh", "-c", "java -jar my-app.jar --server.port=${SERVER_PORT}"]
