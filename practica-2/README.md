# Docker Práctica Básica - Spring Boot CRUD

Este proyecto demuestra la dockerización de un CRUD desarrollado con Spring Boot (Java 21). Incluye la creación de una imagen Docker optimizada y su publicación en Docker Hub.

## 🎯 Objetivos

1. Utilizar una imagen base de Java optimizada en tamaño
2. Implementar multi-stage build para optimizar la creación de la imagen
3. Configurar un volumen para almacenamiento de logs
4. Implementar variables de ambiente para configuración del puerto

## 🔧 Tecnologías Utilizadas

- Java 21
- Spring Boot
- Gradle 8.4
- Docker
- JDK Slim (alternativa ligera a Alpine)

## 🏗️ Estructura del Dockerfile

### Imagen Base
Se utiliza `openjdk:21-jdk-slim` en lugar de Alpine debido a la falta de soporte para JDK 21 en Alpine. Aunque Alpine es más ligero, Slim ofrece un buen balance entre tamaño y compatibilidad.

### Multi-Stage Build
El proceso se divide en dos etapas para optimizar el tamaño final de la imagen:

#### Primera Etapa: Compilación
```dockerfile
FROM gradle:8.4-jdk21 AS build
WORKDIR /app
COPY . /app
RUN chmod +x gradlew
RUN ./gradlew build
```

#### Segunda Etapa: Imagen Final
```dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
RUN mkdir /app/logs
COPY --from=build /app/build/libs/practica-2-0.0.1-SNAPSHOT.jar my-app.jar
ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}
VOLUME /app/logs
ENTRYPOINT ["sh", "-c", "java -jar my-app.jar --server.port=${SERVER_PORT}"]
```

## 🚀 Instrucciones de Uso

### Construcción y Ejecución Local

1. Construir la imagen:
```bash
docker build -t my-app-java .
```

2. Ejecutar el contenedor:
```bash
docker run -d -p 8080:8080 -e SERVER_PORT=8080 -v logs:/app/logs my-app-java
```

3. Verificar los logs:
```bash
docker exec -it <nombre_contenedor> /bin/bash
ls -la /app/logs
```

### Publicación en Docker Hub

1. Instalar dependencias necesarias:
```bash
sudo apt-get install pass gnupg2
```

2. Generar clave GPG:
```bash
gpg --generate-key
```

3. Inicializar almacenamiento Docker:
```bash
pass init <clave_pública>
```

4. Autenticarse en Docker Hub:
```bash
docker login -u <usuario>
```

5. Etiquetar y subir la imagen:
```bash
docker tag <id_imagen> <usuario>/my-app-java:latest
docker push <usuario>/my-app-java:latest
```

## 📝 Configuración de Logs

Los logs de la aplicación se almacenan en el volumen `/app/logs`. La configuración se realiza en `application.properties`:

```properties
logging.file.name=/app/logs/application.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.level.root=INFO
```

## 🔍 Validación

Para verificar que todo funciona correctamente:

1. La aplicación debe estar accesible en el puerto configurado
2. Los logs deben persistir en el volumen después de reiniciar el contenedor
3. El puerto debe ser configurable mediante la variable de ambiente

## 📦 Imagen en Docker Hub

La imagen está disponible en Docker Hub:
```bash
docker pull starlinfrias/my-app-java:latest
```
## Conclusion:

En conclusion podriamos acabar esta practica con el aprendizaje de dockerizar una aplicacion en este caso de Java, utilizando tecnicas bastantes utilices como el Multi-Stage Build, y otras de suma importancia.
