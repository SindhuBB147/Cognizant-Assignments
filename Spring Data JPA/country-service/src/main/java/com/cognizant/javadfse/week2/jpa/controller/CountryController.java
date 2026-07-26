package com.cognizant.javadfse.week2.jpa.controller;

import com.cognizant.javadfse.week2.jpa.model.Country;
import com.cognizant.javadfse.week2.jpa.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAll() { return countryService.getAllCountries(); }

    @GetMapping("/{code}")
    public ResponseEntity<Country> getByCode(@PathVariable String code) {
        return countryService.findByCountryCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @GetMapping("/search")
    public List<Country> search(@RequestParam String name) {
        return countryService.searchByName(name);
    }
}
