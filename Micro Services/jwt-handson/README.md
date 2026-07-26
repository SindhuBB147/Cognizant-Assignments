# jwt-handson — Week 3 JWT Authentication

Spring Security + JWT (jjwt 0.11.5) authentication service for Cognizant DN 5.0.

## Stack

- Java 17, Spring Boot 3.2.5
- Port: `8090`
- Users: `admin/pwd` (ADMIN), `user/pwd` (USER)

## Run

```bash
mvn spring-boot:run
```

## Usage

```bash
# Get JWT (HTTP Basic)
curl -s -u user:pwd http://localhost:8090/authenticate

# Call protected API with Bearer token
curl -s -H "Authorization: Bearer <token>" http://localhost:8090/countries
```
