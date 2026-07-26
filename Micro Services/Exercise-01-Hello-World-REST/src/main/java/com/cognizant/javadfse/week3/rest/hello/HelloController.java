package com.cognizant.javadfse.week3.rest.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World from Spring Boot 3 REST Service";
    }
}
