FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el archivo JAR a una ubicación temporal primero
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
