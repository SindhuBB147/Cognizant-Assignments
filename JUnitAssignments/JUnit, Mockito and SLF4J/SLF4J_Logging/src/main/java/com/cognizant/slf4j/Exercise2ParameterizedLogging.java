package com.cognizant.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise2ParameterizedLogging {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise2ParameterizedLogging.class);

    public static void main(String[] args) {
        logger.info("Starting parameterized logging demonstration");
        
        demonstrateBasicParameterization();
        demonstrateMultipleParameters();
        demonstrateExceptionLogging();
        
        logger.info("Parameterized logging demonstration completed");
    }

    private static void demonstrateBasicParameterization() {
        logger.debug("=== Basic Parameterization ===");
        
        String username = "alice";
        int loginAttempt = 3;
        
        logger.info("User {} attempted login {} times", username, loginAttempt);
        logger.warn("Login attempts exceeded for user {}", username);
        logger.error("Authentication failed for user {}", username);
    }

    private static void demonstrateMultipleParameters() {
        logger.debug("=== Multiple Parameters ===");
        
        String operation = "CREATE";
        String resource = "USER";
        String resourceId = "USR-12345";
        String status = "SUCCESS";
        long duration = 1234;
        
        logger.info("Operation: {} on {}: {} completed in {}ms", operation, resource, resourceId, duration);
        logger.info("Resource created: {} with ID: {} - Status: {}", resource, resourceId, status);
        
        String userId = "USER-001";
        String action = "DELETE";
        String table = "accounts";
        
        logger.info("User {} performed action {} on table {}", userId, action, table);
    }

    private static void demonstrateExceptionLogging() {
        logger.debug("=== Exception Logging ===");
        
        try {
            processTransaction(null);
        } catch (NullPointerException e) {
            logger.error("Transaction processing failed with error: {}", e.getMessage(), e);
        }
        
        try {
            fetchDataFromServer("invalid-endpoint", 5000);
        } catch (Exception e) {
            logger.warn("Retrying request to {} after {}ms timeout", "invalid-endpoint", 5000);
            logger.error("Failed to fetch data from server: {}", e.getMessage());
        }
    }

    private static void processTransaction(Object transaction) {
        if (transaction == null) {
            throw new NullPointerException("Transaction object cannot be null");
        }
        logger.info("Processing transaction: {}", transaction);
    }

    private static void fetchDataFromServer(String endpoint, int timeout) throws Exception {
        logger.info("Fetching data from endpoint: {} with timeout: {}ms", endpoint, timeout);
        throw new Exception("Connection timeout");
    }
}
