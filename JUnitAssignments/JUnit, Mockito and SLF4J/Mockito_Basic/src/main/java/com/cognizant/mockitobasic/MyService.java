package com.cognizant.mockitobasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyService class that depends on ExternalApi.
 * This class demonstrates the use of Mockito for testing external dependencies.
 */
public class MyService {
    
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);
    private final ExternalApi externalApi;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param externalApi the external API dependency
     */
    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }
    
    /**
     * Fetches data from the external API.
     * 
     * @return the data from API
     */
    public String fetchData() {
        logger.info("Fetching data from external API");
        String data = externalApi.getData();
        logger.info("Received data: {}", data);
        return data;
    }
    
    /**
     * Fetches data by ID from the external API.
     * 
     * @param id the ID to fetch
     * @return the data for the given ID
     */
    public String fetchDataById(int id) {
        logger.info("Fetching data for ID: {}", id);
        String data = externalApi.getDataById(id);
        logger.info("Received data: {}", data);
        return data;
    }
    
    /**
     * Processes and sends data to the external API.
     * 
     * @param data the data to send
     */
    public void processAndSendData(String data) {
        logger.info("Processing data: {}", data);
        if (data != null && !data.isEmpty()) {
            logger.info("Sending data to external API");
            externalApi.sendData(data);
            logger.info("Data sent successfully");
        } else {
            logger.warn("Data is empty, not sending");
        }
    }
    
    /**
     * Processes and sends data with priority to the external API.
     * 
     * @param data the data to send
     * @param priority the priority level
     */
    public void processAndSendDataWithPriority(String data, int priority) {
        logger.info("Processing data with priority: {}", priority);
        if (data != null && priority > 0) {
            logger.info("Sending data to external API with priority {}", priority);
            externalApi.sendDataWithPriority(data, priority);
            logger.info("Data sent successfully with priority");
        } else {
            logger.warn("Invalid data or priority");
        }
    }
    
    /**
     * Deletes data by ID.
     * 
     * @param id the ID to delete
     */
    public void deleteDataById(int id) {
        logger.info("Deleting data with ID: {}", id);
        externalApi.deleteData(id);
        logger.info("Data deleted successfully");
    }
    
    /**
     * Validates and sends data.
     * 
     * @param data the data to validate and send
     * @return true if data was validated and sent, false otherwise
     */
    public boolean validateAndSendData(String data) {
        logger.info("Validating data: {}", data);
        if (externalApi.validateData(data)) {
            logger.info("Data is valid, sending");
            externalApi.sendData(data);
            logger.info("Valid data sent");
            return true;
        } else {
            logger.warn("Data validation failed");
            return false;
        }
    }
    
    /**
     * Fetches data multiple times with consecutive calls.
     * 
     * @return array of data fetched on consecutive calls
     */
    public String[] fetchDataMultipleTimes() {
        logger.info("Fetching data multiple times");
        String[] results = new String[3];
        for (int i = 0; i < 3; i++) {
            results[i] = externalApi.getData();
            logger.info("Fetched data ({}): {}", i + 1, results[i]);
        }
        return results;
    }
}
