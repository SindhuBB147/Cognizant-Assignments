package com.example.springapi.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    @NotNull(message = "Country code is required")
    @Size(min = 2, max = 2, message = "Country code should be 2 characters")
    private String code;
    
    private String name;

    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    public Country(String code, String name) {
        LOGGER.debug("Inside Country Parameterized Constructor.");
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        LOGGER.debug("Inside getCode. Value: {}", code);
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("Inside setCode. New Value: {}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("Inside getName. Value: {}", name);
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside setName. New Value: {}", name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
