# Etapa 1: Compilación con Java 21 y Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Compilamos el proyecto omitiendo los tests para ir más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para ejecución (Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Creamos la carpeta para el Wallet de Oracle Cloud
RUN mkdir -p /app/wallet

# Copiamos el JAR generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Copiamos el Wallet desde tu carpeta local al contenedor
# Asegúrate de que los archivos estén en esta ruta relativa en tu proyecto
COPY src/main/resources/wallet/* /app/wallet/

EXPOSE 8084

# Comando para arrancar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]