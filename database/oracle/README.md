# Oracle Setup (ecommerce-304V)

## 1) Crear usuario/schema

Ejecuta [01_create_user.sql](01_create_user.sql) con un usuario DBA (por ejemplo SYS).

## 2) Crear tablas

Conectate como `ECOMMERCE` y ejecuta [02_create_tables.sql](02_create_tables.sql).

## 3) Insertar data semilla

Conectate como `ECOMMERCE` y ejecuta [03_seed_data.sql](03_seed_data.sql).

## 4) Configuracion Spring Boot (Oracle)

Usa estas propiedades en cada microservicio que quieras mover a Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/FREEPDB1
spring.datasource.username=ECOMMERCE
spring.datasource.password=ecommerce_123
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Nota importante del estado actual del proyecto

- `cart` ya usa JPA (`@Entity` + `JpaRepository`), por lo que puede persistir en Oracle con configuracion de datasource.
- `users`, `products`, `inventory`, `orders`, `payment`, `shipping`, `reviews`, `campaigns` aun usan repositorios en memoria (`ConcurrentHashMap`).
- Para persistir esos 8 servicios en Oracle, hay que migrar sus modelos a `@Entity` y reemplazar repositorios por `JpaRepository`.
