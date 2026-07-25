package com.cognizant.slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercise 1: Logging Error Messages and Warning Levels")
public class Exercise1LoggingErrorAndWarningTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise1LoggingErrorAndWarningTest.class);

    @Test
    @DisplayName("Test 1: Logger is initialized correctly")
    public void testLoggerInitialization() {
        logger.debug("Testing logger initialization");
        assertNotNull(logger);
        assertEquals("com.cognizant.slf4j.Exercise1LoggingErrorAndWarningTest", logger.getName());
        logger.info("✓ Logger initialized successfully");
    }

    @Test
    @DisplayName("Test 2: Error logging works")
    public void testErrorLogging() {
        logger.debug("Testing error logging");
        
        try {
            throw new RuntimeException("Test exception");
        } catch (RuntimeException e) {
            logger.error("Caught error: {}", e.getMessage(), e);
        }
        
        logger.info("✓ Error logging completed");
    }

    @Test
    @DisplayName("Test 3: Warning logging works")
    public void testWarningLogging() {
        logger.debug("Testing warning logging");
        
        String value = null;
        if (value == null) {
            logger.warn("Value is null, using default");
        }
        
        logger.info("✓ Warning logging completed");
    }

    @Test
    @DisplayName("Test 4: Info logging works")
    public void testInfoLogging() {
        logger.debug("Testing info logging");
        logger.info("This is an informational message");
        logger.info("✓ Info logging completed");
    }

    @Test
    @DisplayName("Test 5: Debug logging works")
    public void testDebugLogging() {
        logger.debug("Testing debug logging");
        logger.debug("Debug message with details");
        logger.info("✓ Debug logging completed");
    }
}
