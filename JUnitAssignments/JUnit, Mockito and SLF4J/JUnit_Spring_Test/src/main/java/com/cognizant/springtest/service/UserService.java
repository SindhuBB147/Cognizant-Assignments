package com.cognizant.springtest.service;

import com.cognizant.springtest.entity.User;
import com.cognizant.springtest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User Service for Exercise 2 onwards.
 * Handles user business logic.
 */
@Service
public class UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    protected UserRepository userRepository;
    
    /**
     * Setter for test injection of UserRepository.
     * 
     * @param userRepository the repository to set
     */
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * Retrieves user by ID (Exercise 2).
     * 
     * @param id the user's ID
     * @return the user, or null if not found
     */
    public User getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }
    
    /**
     * Saves a new user (Exercise 5).
     * 
     * @param user the user to save
     * @return the saved user
     */
    public User saveUser(User user) {
        logger.info("Saving user: {}", user);
        return userRepository.save(user);
    }
    
    /**
     * Gets user or throws exception if not found (Exercise 6).
     * 
     * @param id the user's ID
     * @return the user
     * @throws java.util.NoSuchElementException if user not found
     */
    public User getUserByIdOrThrow(Long id) {
        logger.info("Fetching user with ID: {} (throw if not found)", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("User not found with id: " + id));
    }
    
    /**
     * Finds users by name (Exercise 7).
     * 
     * @param name the user's name
     * @return list of users with matching name
     */
    public List<User> getUsersByName(String name) {
        logger.info("Finding users with name: {}", name);
        return userRepository.findByName(name);
    }
    
    /**
     * Updates a user.
     * 
     * @param id the user's ID
     * @param user the updated user data
     * @return the updated user
     */
    public User updateUser(Long id, User user) {
        logger.info("Updating user with ID: {}", id);
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setName(user.getName());
            return userRepository.save(userToUpdate);
        }
        return null;
    }
    
    /**
     * Deletes a user by ID.
     * 
     * @param id the user's ID
     */
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
    }
}
