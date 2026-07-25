package com.cognizant.springtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring Boot test class demonstrating component testing.
 */
@SpringBootTest
@DisplayName("MessageService Spring Tests")
public class MessageServiceTest {
    
    @Autowired
    private MessageService messageService;
    
    @Test
    @DisplayName("Should autowire MessageService")
    void testComponentAutowiring() {
        assertNotNull(messageService);
    }
    
    @Test
    @DisplayName("Should create greeting message")
    void testGreeting() {
        // Act
        String result = messageService.greet("Alice");
        
        // Assert
        assertEquals("Hello, Alice!", result);
    }
    
    @Test
    @DisplayName("Should create farewell message")
    void testFarewell() {
        // Act
        String result = messageService.farewell("Bob");
        
        // Assert
        assertEquals("Goodbye, Bob!", result);
    }
}
