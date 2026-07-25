package com.cognizant.mockitoadvanced;

/**
 * Repository interface for database operations.
 * Demonstrates mocking of data access layer.
 */
public interface Repository {
    
    /**
     * Retrieves data from the database.
     * 
     * @return data string
     */
    String getData();
    
    /**
     * Retrieves data by ID from the database.
     * 
     * @param id the ID to retrieve
     * @return data for the given ID
     */
    String getDataById(int id);
    
    /**
     * Saves data to the database.
     * 
     * @param data the data to save
     * @return true if save was successful
     */
    boolean saveData(String data);
    
    /**
     * Deletes data by ID from the database.
     * 
     * @param id the ID to delete
     * @return true if delete was successful
     */
    boolean deleteData(int id);
    
    /**
     * Checks if data exists for given ID.
     * 
     * @param id the ID to check
     * @return true if exists
     */
    boolean exists(int id);
}
