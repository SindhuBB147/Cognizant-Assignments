package com.cognizant.slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercise 2: Parameterized Logging")
public class Exercise2ParameterizedLoggingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise2ParameterizedLoggingTest.class);

    @Test
    @DisplayName("Test 1: Single parameter logging")
    public void testSingleParameterLogging() {
        logger.debug("Testing single parameter logging");
        
        String username = "testuser";
        logger.info("User {} logged in", username);
        
        logger.info("✓ Single parameter logging completed");
    }

    @Test
    @DisplayName("Test 2: Multiple parameters logging")
    public void testMultipleParametersLogging() {
        logger.debug("Testing multiple parameters logging");
        
        String action = "CREATE";
        String resource = "USER";
        long duration = 1234;
        logger.info("Action: {} on {} completed in {}ms", action, resource, duration);
        
        logger.info("✓ Multiple parameters logging completed");
    }

    @Test
    @DisplayName("Test 3: Parameter with exception")
    public void testParameterWithException() {
        logger.debug("Testing parameter logging with exception");
        
        try {
            throw new NullPointerException("Value is null");
        } catch (Exception e) {
            logger.error("Operation failed with error: {}", e.getMessage(), e);
        }
        
        logger.info("✓ Parameter with exception logging completed");
    }

    @Test
    @DisplayName("Test 4: Parameterized logging avoids string concatenation")
    public void testParameterizedLoggingPerformance() {
        logger.debug("Testing parameterized logging performance");
        
        String data = "important data";
        long startTime = System.currentTimeMillis();
        
        logger.debug("Processing: {}", data);
        
        long endTime = System.currentTimeMillis();
        logger.info("Performance test completed in {}ms", endTime - startTime);
    }

    @Test
    @DisplayName("Test 5: Multiple data types in parameters")
    public void testMultipleDataTypesInParameters() {
        logger.debug("Testing multiple data types");
        
        String name = "Alice";
        int count = 42;
        double percentage = 85.5;
        boolean status = true;
        
        logger.info("User: {}, Count: {}, Percentage: {}%, Status: {}", name, count, percentage, status);
        
        logger.info("✓ Multiple data types logging completed");
    }
}
