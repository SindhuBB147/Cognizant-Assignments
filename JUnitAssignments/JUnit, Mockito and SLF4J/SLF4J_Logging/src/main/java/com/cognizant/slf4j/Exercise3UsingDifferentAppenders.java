package com.cognizant.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise3UsingDifferentAppenders {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise3UsingDifferentAppenders.class);

    public static void main(String[] args) {
        logger.debug("Application started with multiple appenders");
        
        logToConsoleAndFile();
        demonstrateLoggingLevels();
        simulateApplicationFlow();
        
        logger.info("Application completed successfully");
    }

    private static void logToConsoleAndFile() {
        logger.debug("=== Logging to Console and File ===");
        
        logger.debug("This DEBUG message goes to both console and file");
        logger.info("This INFO message goes to both console and file");
        logger.warn("This WARN message goes to both console and file");
        logger.error("This ERROR message goes to both console and file");
    }

    private static void demonstrateLoggingLevels() {
        logger.debug("=== Demonstrating Different Logging Levels ===");
        
        logger.debug("DEBUG: Detailed diagnostic information");
        logger.info("INFO: General informational messages");
        logger.warn("WARN: Warning messages for potentially harmful situations");
        logger.error("ERROR: Error messages for error events");
    }

    private static void simulateApplicationFlow() {
        logger.debug("=== Simulating Application Flow ===");
        
        logger.info("Step 1: Initializing database connection");
        simulateDatabaseOperation();
        
        logger.info("Step 2: Processing user requests");
        simulateUserProcessing();
        
        logger.info("Step 3: Generating reports");
        simulateReportGeneration();
        
        logger.info("Step 4: Cleaning up resources");
        logger.debug("All resources cleaned up successfully");
    }

    private static void simulateDatabaseOperation() {
        logger.debug("Connecting to database at localhost:5432");
        logger.debug("Executing query: SELECT * FROM users");
        logger.info("Database query completed successfully");
        logger.debug("Result set contains 150 records");
    }

    private static void simulateUserProcessing() {
        try {
            for (int i = 1; i <= 5; i++) {
                logger.debug("Processing user request {}", i);
                
                if (i == 3) {
                    logger.warn("User request {} took longer than expected", i);
                }
                
                logger.info("User request {} processed", i);
            }
        } catch (Exception e) {
            logger.error("Error during user processing: {}", e.getMessage(), e);
        }
    }

    private static void simulateReportGeneration() {
        logger.debug("Starting report generation");
        
        String[] reports = {"Daily Report", "Weekly Report", "Monthly Report"};
        
        for (String report : reports) {
            logger.info("Generating: {}", report);
            logger.debug("Report {} format: PDF", report);
        }
        
        logger.info("All reports generated successfully");
    }
}
