package com.example.springapi.controller;

import com.example.springapi.model.Country;
import com.example.springapi.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    // ApplicationContext: to retrieve spring-bean from container
    private final ApplicationContext applicationContext;
    private final CountryService countryService;

    public CountryController(ApplicationContext applicationContext, CountryService countryService) {
        this.applicationContext = applicationContext;
        this.countryService = countryService;
    }

    @GetMapping("/country")
    public Country getCountryIndia()
    {
        logger.info("START");

        Country country = applicationContext.getBean("country", Country.class);

        logger.info("END");

        return country;
    }

    /* Working
Browser-> GET/country -> DispatcherServlet -> CountryController-> ApplicationContext ->country.xml -> Country Bean -> Jackson -> JSON
 */

    @GetMapping("/countries")
    public List<Country> getAllCountry()
    {
        logger.info("START");

        List<Country> countryList = countryService.getAllCountries();

        logger.info("END");

        return countryList;
    }

    @GetMapping("/countries/{code}")
    public Country getCountryByCode(@PathVariable String code)
    {
        Country country = countryService.getCountry(code);
        return country;
    }
}

