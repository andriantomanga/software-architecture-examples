# Kontactsphere

Un exemple d'application développée avec **Spring Boot**🍃🍃🍃 sur la base de l' **architecture Hexagonale**.

## Prérequis
Assurez-vous d'avoir installé Docker 🐋. 

Sinon, vous devrez installer un serveur PostgreSQL 🐘 sur votre machine et ajouter la configuration de la base de données dans le fichier ```application.properties```.
```properties
spring.application.name=kontactsphere

spring.datasource.url=jdbc:postgresql://localhost:5432/kontactsphere
spring.datasource.username=postgres
spring.datasource.password=azerty
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Execution

### 1. Lancement de l'application
 ```bash
docker-compose up
```

### 2. Tests
#### 2.1. Ecriture
 ```bash
curl -X POST \
http://localhost:8080/v1/contacts \
-H 'Content-Type: application/json' \
-d '{
"name": "patrick4",
"email": "patrick4@gmail.com",
"phoneNumber": "0605798879"
}'
 ```
#### 2.2. Lecture
 ```bash
curl -X GET http://localhost:8080/v1/contacts/1
 ```
