package com.cognizant.mockitoadvanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for Exercise 2: Mocking External Services (RESTful APIs).
 * Demonstrates testing with mocked REST client.
 */
public class ApiService {
    
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private final RestClient restClient;
    
    /**
     * Constructor with REST client dependency injection.
     */
    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }
    
    /**
     * Fetches data from REST API.
     */
    public String fetchData() {
        logger.info("Fetching data from REST API");
        String response = restClient.getResponse();
        if (response != null) {
            String result = "Fetched " + response;
            logger.info("API Response: {}", result);
            return result;
        }
        return "No response";
    }
    
    /**
     * Fetches data by ID from REST API.
     */
    public String fetchDataById(int id) {
        logger.info("Fetching data by ID: {} from API", id);
        return "Fetched " + restClient.getResponseById(id);
    }
    
    /**
     * Posts data to REST API.
     */
    public String postDataToApi(String data) {
        logger.info("Posting data to API: {}", data);
        return "Posted " + restClient.postData(data);
    }
    
    /**
     * Updates data via REST API.
     */
    public String updateData(int id, String data) {
        logger.info("Updating data ID {} with: {}", id, data);
        return "Updated " + restClient.putData(id, data);
    }
    
    /**
     * Deletes resource via REST API.
     */
    public String deleteResource(int id) {
        logger.info("Deleting resource ID: {}", id);
        return "Deleted " + restClient.deleteResource(id);
    }
}
