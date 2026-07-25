package com.cognizant.mockitoadvanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for Exercise 1: Mocking Databases and Repositories.
 * Demonstrates testing service logic with mocked repository.
 */
public class Service {
    
    private static final Logger logger = LoggerFactory.getLogger(Service.class);
    private final Repository repository;
    
    /**
     * Constructor with repository dependency injection.
     */
    public Service(Repository repository) {
        this.repository = repository;
    }
    
    /**
     * Processes data from the repository.
     */
    public String processData() {
        logger.info("Processing data from repository");
        String data = repository.getData();
        if (data != null) {
            String processed = "Processed " + data;
            logger.info("Data processed: {}", processed);
            return processed;
        }
        return "No data";
    }
    
    /**
     * Processes data by ID.
     */
    public String processDataById(int id) {
        logger.info("Processing data for ID: {}", id);
        if (repository.exists(id)) {
            String data = repository.getDataById(id);
            return "Processed " + data;
        }
        return "ID not found";
    }
    
    /**
     * Saves processed data.
     */
    public boolean saveProcessedData(String data) {
        logger.info("Saving processed data: {}", data);
        return repository.saveData(data);
    }
}
