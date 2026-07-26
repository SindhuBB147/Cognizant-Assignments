package com.cognizant.javadfse.week3.rest.country;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:country-beans.xml")
public class CountryRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CountryRestApplication.class, args);
    }
}
