package com.cognizant.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise1LoggingErrorAndWarning {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise1LoggingErrorAndWarning.class);

    public static void main(String[] args) {
        logger.debug("Application started");
        
        try {
            processData("validData");
        } catch (Exception e) {
            logger.error("Error processing data: {}", e.getMessage(), e);
        }
        
        validateInput(null);
        
        logger.info("Application execution completed");
    }

    private static void processData(String data) throws Exception {
        logger.debug("Processing data: {}", data);
        
        if (data == null || data.isEmpty()) {
            logger.warn("Empty data provided, using default value");
            return;
        }
        
        logger.info("Data processed successfully: {}", data);
    }

    private static void validateInput(String input) {
        logger.debug("Validating input");
        
        if (input == null) {
            logger.warn("Input is null, validation skipped");
            return;
        }
        
        if (input.length() < 5) {
            logger.warn("Input length is less than 5 characters");
            return;
        }
        
        logger.info("Input validation passed");
    }
}
