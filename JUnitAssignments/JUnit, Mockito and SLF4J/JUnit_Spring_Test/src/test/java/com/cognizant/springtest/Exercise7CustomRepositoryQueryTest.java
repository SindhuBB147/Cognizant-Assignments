package com.cognizant.springtest;

import com.cognizant.springtest.entity.User;
import com.cognizant.springtest.repository.UserRepository;
import com.cognizant.springtest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 7: Test Custom Repository Query
 * 
 * Tests custom query methods in UserRepository (findByName)
 */
@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Exercise 7: Test Custom Repository Query")
public class Exercise7CustomRepositoryQueryTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise7CustomRepositoryQueryTest.class);
    
    @Autowired
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Clear repository and initialize UserService ===");
        userRepository.deleteAll();
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }
    
    /**
     * Test findByName returns users with matching name.
     */
    @Test
    @DisplayName("Test 1: findByName returns users with matching name")
    public void testFindByName() {
        logger.info("--- ARRANGE: Save users with different names ---");
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Bob"));
        userRepository.save(new User("Alice"));
        
        logger.info("--- ACT: Find users by name 'Alice' ---");
        List<User> result = userRepository.findByName("Alice");
        
        logger.info("--- ASSERT: Verify 2 users found ---");
        assertEquals(2, result.size());
        result.forEach(user -> assertEquals("Alice", user.getName()));
        logger.info("✓ Test passed");
    }
    
    /**
     * Test findByName returns empty list when no matches.
     */
    @Test
    @DisplayName("Test 2: findByName returns empty list when no matches")
    public void testFindByNameNoMatches() {
        logger.info("--- ARRANGE: Save users ---");
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Bob"));
        
        logger.info("--- ACT: Find users by non-existent name ---");
        List<User> result = userRepository.findByName("Charlie");
        
        logger.info("--- ASSERT: Verify empty list ---");
        assertTrue(result.isEmpty());
        logger.info("✓ Test passed");
    }
    
    /**
     * Test findByName with single match.
     */
    @Test
    @DisplayName("Test 3: findByName with single match")
    public void testFindByNameSingleMatch() {
        logger.info("--- ARRANGE: Save users ---");
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Bob"));
        userRepository.save(new User("Charlie"));
        
        logger.info("--- ACT: Find user by name 'Bob' ---");
        List<User> result = userRepository.findByName("Bob");
        
        logger.info("--- ASSERT: Verify 1 user found ---");
        assertEquals(1, result.size());
        assertEquals("Bob", result.get(0).getName());
        logger.info("✓ Test passed");
    }
    
    /**
     * Test UserService.getUsersByName uses repository query.
     */
    @Test
    @DisplayName("Test 4: UserService.getUsersByName uses custom query")
    public void testUserServiceGetUsersByName() {
        logger.info("--- ARRANGE: Save users ---");
        userRepository.save(new User("Test User"));
        userRepository.save(new User("Another User"));
        userRepository.save(new User("Test User"));
        
        logger.info("--- ACT: Call service method ---");
        List<User> result = userService.getUsersByName("Test User");
        
        logger.info("--- ASSERT: Verify results ---");
        assertEquals(2, result.size());
        result.forEach(user -> assertEquals("Test User", user.getName()));
        logger.info("✓ Test passed");
    }
    
    /**
     * Test findByName with special characters.
     */
    @Test
    @DisplayName("Test 5: findByName handles special characters")
    public void testFindByNameSpecialCharacters() {
        logger.info("--- ARRANGE: Save user with special name ---");
        userRepository.save(new User("O'Brien"));
        userRepository.save(new User("John-Doe"));
        
        logger.info("--- ACT: Find users with special characters ---");
        List<User> result1 = userRepository.findByName("O'Brien");
        List<User> result2 = userRepository.findByName("John-Doe");
        
        logger.info("--- ASSERT: Verify results ---");
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
        logger.info("✓ Test passed");
    }
}
