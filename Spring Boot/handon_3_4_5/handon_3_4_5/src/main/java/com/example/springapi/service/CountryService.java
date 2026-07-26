package com.example.springapi.service;

import com.example.springapi.model.Country;
import com.example.springapi.service.exception.CountryNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final ApplicationContext applicationContext;

    public CountryService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<Country> getAllCountries() {
        @SuppressWarnings("unchecked")
        List<Country> countryList = (List<Country>) applicationContext.getBean("countryList");
        return countryList;
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        List<Country> countryList = getAllCountries();

        for (Country country : countryList) {
            if (country.getCode().equalsIgnoreCase(code)) {
                return country;
            }
        }
        throw new CountryNotFoundException("Country with code " + code + " not found.");
    }
}
