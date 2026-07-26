package com.cognizant.javadfse.week2.jpa.service;

import com.cognizant.javadfse.week2.jpa.model.Country;
import com.cognizant.javadfse.week2.jpa.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() { return countryRepository.findAll(); }

    public Optional<Country> findByCountryCode(String code) {
        return countryRepository.findByCountryCode(code);
    }

    public Country addCountry(Country country) { return countryRepository.save(country); }

    public Optional<Country> findByCodeUsingHql(String code) {
        return countryRepository.findCountryByCodeHql(code);
    }

    public Optional<Country> findByCodeUsingNativeQuery(String code) {
        return countryRepository.findCountryByCodeNative(code);
    }

    public List<Country> searchByName(String name) {
        return countryRepository.findByCountryNameContainingIgnoreCase(name);
    }
}
