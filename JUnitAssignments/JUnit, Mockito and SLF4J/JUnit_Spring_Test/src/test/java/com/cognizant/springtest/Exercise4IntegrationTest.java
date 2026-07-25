package com.cognizant.springtest;

import com.cognizant.springtest.entity.User;
import com.cognizant.springtest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 4: Integration Test with Spring Boot
 * 
 * Tests the full flow from repository to controller using @DataJpaTest
 */
@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Exercise 4: Integration Test with Spring Boot")
public class Exercise4IntegrationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise4IntegrationTest.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Clear repository and prepare test data ===");
        userRepository.deleteAll();
    }
    
    /**
     * Test saving a user to the database.
     */
    @Test
    @DisplayName("Test 1: Save and retrieve user")
    public void testSaveAndRetrieveUser() {
        logger.info("--- ARRANGE: Create a new user ---");
        User newUser = new User("John Doe");
        
        logger.info("--- ACT: Save user to database ---");
        User savedUser = userRepository.save(newUser);
        
        logger.info("--- ASSERT: Verify user was saved ---");
        assertNotNull(savedUser.getId());
        assertEquals("John Doe", savedUser.getName());
        
        logger.info("--- ACT: Retrieve user from database ---");
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        
        logger.info("--- ASSERT: Verify retrieval ---");
        assertTrue(retrievedUser.isPresent());
        assertEquals("John Doe", retrievedUser.get().getName());
        logger.info("✓ Test passed");
    }
    
    /**
     * Test updating a user.
     */
    @Test
    @DisplayName("Test 2: Update user")
    public void testUpdateUser() {
        logger.info("--- ARRANGE: Create and save a user ---");
        User user = new User("Original Name");
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();
        
        logger.info("--- ACT: Update user name ---");
        User userToUpdate = userRepository.findById(userId).get();
        userToUpdate.setName("Updated Name");
        userRepository.save(userToUpdate);
        
        logger.info("--- ASSERT: Verify update ---");
        User retrievedUser = userRepository.findById(userId).get();
        assertEquals("Updated Name", retrievedUser.getName());
        logger.info("✓ Test passed");
    }
    
    /**
     * Test deleting a user.
     */
    @Test
    @DisplayName("Test 3: Delete user")
    public void testDeleteUser() {
        logger.info("--- ARRANGE: Create and save a user ---");
        User user = new User("User to Delete");
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();
        
        logger.info("--- ACT: Delete user ---");
        userRepository.deleteById(userId);
        
        logger.info("--- ASSERT: Verify deletion ---");
        Optional<User> retrievedUser = userRepository.findById(userId);
        assertTrue(retrievedUser.isEmpty());
        logger.info("✓ Test passed");
    }
    
    /**
     * Test finding by custom query (findByName).
     */
    @Test
    @DisplayName("Test 4: Find users by name")
    public void testFindByName() {
        logger.info("--- ARRANGE: Create and save multiple users ---");
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Bob"));
        userRepository.save(new User("Alice")); // Duplicate name
        
        logger.info("--- ACT: Find users by name ---");
        var aliceUsers = userRepository.findByName("Alice");
        var bobUsers = userRepository.findByName("Bob");
        
        logger.info("--- ASSERT: Verify results ---");
        assertEquals(2, aliceUsers.size());
        assertEquals(1, bobUsers.size());
        logger.info("✓ Test passed");
    }
}
