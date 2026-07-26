package com.example.springapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.springapi.model.Country;

@SpringBootApplication
public class SpringapiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringapiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringapiApplication.class, args);
        
        // Execute Hands-on methods
        displayDate();
        displayCountry();
        displayCountries();
    }

    public static void displayDate() {
        LOGGER.info("START displayDate");
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        try {
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Parsed Date: {}", date);
        } catch (Exception e) {
            LOGGER.error("Error parsing date: ", e);
        }
        LOGGER.info("END displayDate");
    }

    public static void displayCountry() {
        LOGGER.info("START displayCountry");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        Country anotherCountry = context.getBean("country", Country.class);
        
        LOGGER.debug("Country: {}", country);
        LOGGER.debug("Another Country: {}", anotherCountry);
        LOGGER.debug("Are bean references equal? {}", (country == anotherCountry));
        LOGGER.info("END displayCountry");
    }

    public static void displayCountries() {
        LOGGER.info("START displayCountries");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        @SuppressWarnings("unchecked")
        List<Country> countryList = context.getBean("countryList", List.class);
        LOGGER.debug("Countries List: {}", countryList);
        LOGGER.info("END displayCountries");
    }
}
