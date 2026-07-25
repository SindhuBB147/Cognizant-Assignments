package com.cognizant.mockitobasic;

/**
 * External API interface to be mocked.
 * This represents an external service that our code depends on.
 */
public interface ExternalApi {
    
    /**
     * Fetches data from the external API.
     * 
     * @return data string from API
     */
    String getData();
    
    /**
     * Fetches data with a specific ID.
     * 
     * @param id the ID to fetch data for
     * @return data for the given ID
     */
    String getDataById(int id);
    
    /**
     * Sends data to the external API.
     * 
     * @param data the data to send
     */
    void sendData(String data);
    
    /**
     * Sends data with a specific priority.
     * 
     * @param data the data to send
     * @param priority the priority level
     */
    void sendDataWithPriority(String data, int priority);
    
    /**
     * Deletes data by ID.
     * 
     * @param id the ID to delete
     */
    void deleteData(int id);
    
    /**
     * Validates the provided data.
     * 
     * @param data the data to validate
     * @return true if data is valid, false otherwise
     */
    boolean validateData(String data);
}
