
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN mkdir -p /app/wallet
COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/wallet/* /app/wallet/

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]