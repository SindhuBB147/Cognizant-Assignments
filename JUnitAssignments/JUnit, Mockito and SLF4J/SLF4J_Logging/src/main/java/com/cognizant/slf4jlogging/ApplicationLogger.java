package com.cognizant.slf4jlogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample class demonstrating SLF4J logging best practices.
 */
public class ApplicationLogger {
    
    // Logger should be static final
    private static final Logger logger = LoggerFactory.getLogger(ApplicationLogger.class);
    
    /**
     * Logs application startup message.
     * 
     * @param appName the application name
     */
    public void logStartup(String appName) {
        logger.info("Application {} is starting", appName);
    }
    
    /**
     * Logs application shutdown message.
     * 
     * @param appName the application name
     */
    public void logShutdown(String appName) {
        logger.info("Application {} is shutting down", appName);
    }
    
    /**
     * Logs a warning message.
     * 
     * @param message the warning message
     */
    public void logWarning(String message) {
        logger.warn("Warning: {}", message);
    }
    
    /**
     * Logs an error message with exception.
     * 
     * @param message the error message
     * @param exception the exception
     */
    public void logError(String message, Exception exception) {
        logger.error("Error: {}", message, exception);
    }
}
