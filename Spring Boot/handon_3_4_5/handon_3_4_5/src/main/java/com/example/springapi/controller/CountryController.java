package com.example.springapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.model.Country;
import com.example.springapi.service.CountryService;
import com.example.springapi.service.exception.CountryNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final ApplicationContext applicationContext;
    private final CountryService countryService;

    public CountryController(ApplicationContext applicationContext, CountryService countryService) {
        this.applicationContext = applicationContext;
        this.countryService = countryService;
    }

    @GetMapping("/india")
    public Country getCountryIndia() {
        LOGGER.info("START getCountryIndia");
        Country country = applicationContext.getBean("country", Country.class);
        LOGGER.info("END getCountryIndia");
        return country;
    }

    @GetMapping
    public List<Country> getAllCountry() {
        LOGGER.info("START getAllCountry");
        List<Country> countryList = countryService.getAllCountries();
        LOGGER.info("END getAllCountry");
        return countryList;
    }

    @GetMapping("/{code}")
    public Country getCountryByCode(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START getCountryByCode: {}", code);
        Country country = countryService.getCountry(code);
        LOGGER.info("END getCountryByCode");
        return country;
    }

    @PostMapping
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("START addCountry: {}", country.getCode());
        // Simple mock behavior returning the passed country
        LOGGER.info("END addCountry");
        return country;
    }
}
