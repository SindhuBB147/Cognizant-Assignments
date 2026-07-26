package com.cognizant.javadfse.week2.jpa.repository;

import com.cognizant.javadfse.week2.jpa.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByCountryCode(String countryCode);
    List<Country> findByCountryNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Country c WHERE c.countryCode = :code")
    Optional<Country> findCountryByCodeHql(@Param("code") String code);

    @Query(value = "SELECT * FROM country WHERE country_code = :code", nativeQuery = true)
    Optional<Country> findCountryByCodeNative(@Param("code") String code);
}
