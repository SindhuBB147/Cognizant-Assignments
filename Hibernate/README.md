# Hibernate Hands-on (Week 2)

The mandatory Hibernate and O/R mapping exercises are implemented in the shared **country-service** Spring Data JPA project.

## Covered Mandatory Items

| Exercise | Location |
|----------|----------|
| O/R Mapping with JPA `@Entity` | `../Spring Data JPA/country-service/src/main/java/.../model/Country.java` |
| Query Methods | `CountryRepository.findByCountryCode()` |
| HQL Query | `CountryRepository.findCountryByCodeHql()` |
| Native SQL Query | `CountryRepository.findCountryByCodeNative()` |

## How to Run

```bash
cd "../Spring Data JPA/country-service"
mvn spring-boot:run
curl http://localhost:8080/api/countries/IN
```

## Expected Output

Hibernate SQL trace in console and JSON country response from the REST API.
