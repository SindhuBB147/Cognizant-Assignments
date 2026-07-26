# Week 3 - Spring REST (Complete)

## Projects

| Project | Description | Port |
|---------|-------------|------|
| `Exercise-01-Hello-World-REST` | Early Hello World REST | 8080 |
| `Exercise-02-Country-REST-Service` | Country REST + XML bean | 8080 |
| `spring-learn` | Full curriculum HO 1–6 + REST + employee optional | 8083 |
| `jwt-handson` | JWT authentication (mandatory) | 8090 |

## spring-learn quick start

```bash
cd spring-learn
mvn spring-boot:run
```

- GET `/hello` → `Hello World!!`
- GET `/country` → India
- GET `/countries/{code}` → case-insensitive lookup
- GET `/employees`, GET `/departments`

## jwt-handson quick start

```bash
cd jwt-handson
mvn spring-boot:run
curl -s -u user:pwd http://localhost:8090/authenticate
curl -s -H "Authorization: Bearer <token>" http://localhost:8090/countries
```
