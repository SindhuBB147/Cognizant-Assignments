package com.cognizant.mockitobasic;

/**
 * Service interface for user operations.
 * Will be mocked in tests.
 */
public interface UserRepository {
    
    /**
     * Finds a user by ID.
     * 
     * @param userId the user ID
     * @return the User object or null if not found
     */
    User findById(String userId);
    
    /**
     * Saves a user to the repository.
     * 
     * @param user the user to save
     * @return true if saved successfully, false otherwise
     */
    boolean save(User user);
}
