# Spring Data JPA Country Service

## Problem Statement
Implement Country CRUD with query methods, HQL, and native SQL using H2 database.

## Approach
JPA entity mapping, Spring Data derived queries, @Query HQL and nativeQuery; service layer orchestration.

## How to Run
cd "Week-2/Spring Data JPA/country-service"
mvn spring-boot:run
# GET http://localhost:8080/api/countries/IN

## Expected Output
```
JSON country records returned; HQL and native queries resolve by country code
```
