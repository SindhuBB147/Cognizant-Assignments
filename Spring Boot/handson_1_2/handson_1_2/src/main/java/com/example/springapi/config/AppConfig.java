package com.example.springapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:country.xml", "classpath:date-format.xml"})
// @ImportResource: It loads XML configuration file into a SB application
public class AppConfig {
}
