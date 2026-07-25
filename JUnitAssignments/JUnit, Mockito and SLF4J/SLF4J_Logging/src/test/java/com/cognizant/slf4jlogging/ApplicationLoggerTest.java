package com.cognizant.slf4jlogging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ApplicationLogger demonstrating logging in tests.
 */
@DisplayName("ApplicationLogger Tests")
public class ApplicationLoggerTest {
    
    private ApplicationLogger logger;
    
    @BeforeEach
    void setUp() {
        logger = new ApplicationLogger();
    }
    
    @Test
    @DisplayName("Should log startup message without throwing exception")
    void testLogStartup() {
        assertDoesNotThrow(() -> logger.logStartup("TestApp"));
    }
    
    @Test
    @DisplayName("Should log shutdown message without throwing exception")
    void testLogShutdown() {
        assertDoesNotThrow(() -> logger.logShutdown("TestApp"));
    }
    
    @Test
    @DisplayName("Should log warning message without throwing exception")
    void testLogWarning() {
        assertDoesNotThrow(() -> logger.logWarning("This is a test warning"));
    }
    
    @Test
    @DisplayName("Should log error message with exception without throwing exception")
    void testLogError() {
        Exception testException = new RuntimeException("Test error");
        assertDoesNotThrow(() -> logger.logError("Test error occurred", testException));
    }
}
