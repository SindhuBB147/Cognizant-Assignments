package com.cognizant.javadfse.week3.rest.country.repository;

import com.cognizant.javadfse.week3.rest.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByCountryCode(String countryCode);
}
