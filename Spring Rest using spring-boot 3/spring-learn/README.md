# spring-learn — Week 3 Spring REST Hands-on

Cognizant DN 5.0 curriculum project covering Spring Boot XML beans, REST, validation, and employee/department optional services.

## Stack

- Java 17, Spring Boot 3.2.5
- Port: `8083`

## Run

```bash
mvn spring-boot:run
```

## Key endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/hello` | Hello World!! |
| GET | `/country` | India from XML |
| GET | `/countries` | All countries |
| GET | `/countries/{code}` | Country by code (case-insensitive) |
| POST | `/countries` | Add country (`@Valid`) |
| GET | `/employees` | All employees |
| PUT | `/employees` | Update employee |
| DELETE | `/employees/{id}` | Delete employee |
| GET | `/departments` | All departments |

## Tests

```bash
mvn test
```
