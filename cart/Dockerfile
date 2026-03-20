# Usamos una imagen de Java 17 ligera
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Creamos la carpeta para el Wallet de Oracle
RUN mkdir -p /app/wallet

# Copiamos el JAR (ajusta el nombre si es necesario)
COPY target/*.jar app.jar

# Copiamos los archivos del Wallet desde tu ruta local al contenedor
COPY src/main/resources/wallet/* /app/wallet/

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]