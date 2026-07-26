package com.example.springapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:country.xml", "classpath:employee.xml"})
public class AppConfig {
}
