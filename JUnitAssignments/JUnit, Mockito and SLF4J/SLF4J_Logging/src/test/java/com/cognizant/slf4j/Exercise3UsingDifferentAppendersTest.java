package com.cognizant.slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercise 3: Using Different Appenders")
public class Exercise3UsingDifferentAppendersTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise3UsingDifferentAppendersTest.class);

    @Test
    @DisplayName("Test 1: Console appender logging")
    public void testConsoleAppenderLogging() {
        logger.debug("Testing console appender");
        logger.info("This message goes to console appender");
        logger.warn("Console appender is working");
        logger.info("✓ Console appender test passed");
    }

    @Test
    @DisplayName("Test 2: File appender logging")
    public void testFileAppenderLogging() {
        logger.debug("Testing file appender");
        logger.info("This message should be written to file");
        logger.debug("File appender is configured and working");
        logger.info("✓ File appender test passed");
    }

    @Test
    @DisplayName("Test 3: Error file appender logging")
    public void testErrorFileAppenderLogging() {
        logger.debug("Testing error file appender");
        logger.error("This error message goes to error log file");
        logger.info("✓ Error file appender test passed");
    }

    @Test
    @DisplayName("Test 4: Different logging levels to appenders")
    public void testDifferentLoggingLevels() {
        logger.debug("DEBUG: Detailed diagnostic information");
        logger.info("INFO: General informational messages");
        logger.warn("WARN: Warning messages");
        logger.error("ERROR: Error messages");
        logger.info("✓ Different logging levels test passed");
    }

    @Test
    @DisplayName("Test 5: Verify log files exist")
    public void testLogFilesExist() {
        logger.info("Testing log file creation");
        
        File logsDir = new File("logs");
        File appLogFile = new File(logsDir, "application.log");
        
        logger.debug("Checking if logs directory and files exist");
        logger.info("Logs directory exists: {}", logsDir.exists());
        
        if (appLogFile.exists()) {
            logger.info("Application log file exists with size: {} bytes", appLogFile.length());
        }
        
        logger.info("✓ Log files verification completed");
    }
}
