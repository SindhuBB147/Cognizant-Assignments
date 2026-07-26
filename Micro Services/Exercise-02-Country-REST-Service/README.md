# REST Country Web Service

## Problem Statement
Expose country CRUD APIs and load a default country bean from Spring XML configuration.

## Approach
Spring Data JPA with H2, @ImportResource for XML bean wiring, REST controller for list/find/create operations.

## How to Run
cd "Week-3/Spring REST/Exercise-02-Country-REST-Service"
mvn spring-boot:run
curl http://localhost:8081/countries/UK

## Expected Output
```
JSON country payload for UK; /countries/xml-default returns India from XML config
```
