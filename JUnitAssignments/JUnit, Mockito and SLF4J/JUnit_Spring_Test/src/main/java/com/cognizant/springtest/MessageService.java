package com.cognizant.springtest;

import org.springframework.stereotype.Component;

/**
 * Placeholder component for Spring Testing module.
 */
@Component
public class MessageService {
    
    /**
     * Creates a greeting message.
     * 
     * @param name the person's name
     * @return greeting message
     */
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
    
    /**
     * Creates a farewell message.
     * 
     * @param name the person's name
     * @return farewell message
     */
    public String farewell(String name) {
        return "Goodbye, " + name + "!";
    }
}
