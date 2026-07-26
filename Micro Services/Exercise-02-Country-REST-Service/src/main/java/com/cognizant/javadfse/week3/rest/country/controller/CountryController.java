package com.cognizant.javadfse.week3.rest.country.controller;

import com.cognizant.javadfse.week3.rest.country.model.Country;
import com.cognizant.javadfse.week3.rest.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryRepository countryRepository;
    private final Country xmlCountry;

    public CountryController(CountryRepository countryRepository, @Qualifier("defaultCountry") Country xmlCountry) {
        this.countryRepository = countryRepository;
        this.xmlCountry = xmlCountry;
    }

    @GetMapping
    public List<Country> getAll() { return countryRepository.findAll(); }

    @GetMapping("/xml-default")
    public Country getXmlConfiguredCountry() { return xmlCountry; }

    @GetMapping("/{code}")
    public ResponseEntity<Country> getByCode(@PathVariable String code) {
        return countryRepository.findByCountryCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }
}
