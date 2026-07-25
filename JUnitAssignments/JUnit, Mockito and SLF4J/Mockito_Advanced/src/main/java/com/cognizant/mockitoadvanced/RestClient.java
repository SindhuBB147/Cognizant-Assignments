package com.cognizant.mockitoadvanced;

/**
 * RestClient interface for REST API interactions.
 * Demonstrates mocking of external HTTP services.
 */
public interface RestClient {
    
    /**
     * Makes a GET request and returns response.
     * 
     * @return response string
     */
    String getResponse();
    
    /**
     * Makes a GET request with a specific ID.
     * 
     * @param id the resource ID
     * @return response for the given ID
     */
    String getResponseById(int id);
    
    /**
     * Makes a POST request with data.
     * 
     * @param data the data to post
     * @return response from server
     */
    String postData(String data);
    
    /**
     * Makes a PUT request with data and ID.
     * 
     * @param id the resource ID
     * @param data the data to update
     * @return response from server
     */
    String putData(int id, String data);
    
    /**
     * Makes a DELETE request.
     * 
     * @param id the resource ID to delete
     * @return response from server
     */
    String deleteResource(int id);
}
