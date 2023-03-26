# Microservices

Demo project which is based on:

## Tech stack
1. Spring Boot:
   1. WebFlux (Reactive)
   2. R2DBC (Reactive access to Postgres)
   3. Validation
   4. HATEOAS
2. Spring Cloud:
   1. Actuator
   2. Cloud Stream (Kafka)
   3. Eureka Server and Client
3. Database:
   1. Postgres + Liquibase
4. Messaging:
   1. Kafka + Zookeeper

## Domain
The project consists of the following services:
1. Order service - to manage orders
2. Customer service - to manage customers
3. Eureka Registry - Eureka Discovery service

## Testing
Implemented e2e tests "microservices-end-to-end-tests" based on Cucumber

## Set Up
1. Java 17
2. Maven
3. Docker Compose which runs Postgres, PGAdmin, Kafka, Zookepeer and Eureka Server.