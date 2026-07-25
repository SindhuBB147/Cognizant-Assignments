package com.cognizant.springtest;

import com.cognizant.springtest.entity.User;
import com.cognizant.springtest.repository.UserRepository;
import com.cognizant.springtest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

/**
 * Exercise 11: Mocking a Repository in a Service Test
 * 
 * Task: Write a unit test for a Spring service that uses a repository to fetch data.
 * Mock the repository dependency using Mockito.
 * 
 * This exercise demonstrates:
 * - How to mock repository dependencies in service tests
 * - Using Optional in mocked repository responses
 * - Verifying repository method calls
 * - Testing service logic with mocked data access
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 11: Mocking a Repository in a Service Test")
public class Exercise11MockingRepositoryInServiceTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise11MockingRepositoryInServiceTest.class);
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize UserService with mocked UserRepository ===");
        userService = new UserService();
        userService.setUserRepository(userRepository);
        logger.info("✓ Service initialized with mocked repository");
    }
    
    /**
     * Test 1: getUserById returns user when repository finds it
     * 
     * ARRANGE: Mock repository to return Optional with user
     * ACT: Call service method
     * ASSERT: Verify returned user and repository interaction
     */
    @Test
    @DisplayName("Test 1: getUserById returns user when repository has user")
    public void testGetUserByIdReturnsUserWhenRepositoryHasUser() {
        logger.info("--- ARRANGE: Create mock user and configure repository ---");
        User mockUser = new User(1L, "Alice Johnson");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        
        logger.info("--- ACT: Call service method ---");
        User result = userService.getUserById(1L);
        
        logger.info("--- ASSERT: Verify returned user ---");
        assertNotNull(result, "User should not be null");
        assertEquals(1L, result.getId(), "User ID should match");
        assertEquals("Alice Johnson", result.getName(), "User name should match");
        
        logger.info("--- VERIFY: Repository findById was called once ---");
        verify(userRepository, times(1)).findById(1L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 2: getUserById returns null when repository doesn't find user
     * 
     * Demonstrates handling of Optional.empty() from repository
     */
    @Test
    @DisplayName("Test 2: getUserById returns null when repository doesn't have user")
    public void testGetUserByIdReturnsNullWhenRepositoryHasNoUser() {
        logger.info("--- ARRANGE: Configure repository to return empty Optional ---");
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        
        logger.info("--- ACT: Call service method ---");
        User result = userService.getUserById(99L);
        
        logger.info("--- ASSERT: Verify result is null ---");
        assertNull(result, "User should be null when not found");
        
        logger.info("--- VERIFY: Repository findById was called ---");
        verify(userRepository).findById(99L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 3: getUserById is called with correct ID
     * 
     * Demonstrates verification of method arguments
     */
    @Test
    @DisplayName("Test 3: Verify repository findById is called with correct ID")
    public void testVerifyRepositoryFindByIdCalledWithCorrectId() {
        logger.info("--- ARRANGE: Configure repository mock ---");
        User mockUser = new User(5L, "Bob Wilson");
        when(userRepository.findById(5L)).thenReturn(Optional.of(mockUser));
        
        logger.info("--- ACT: Call service ---");
        User result = userService.getUserById(5L);
        
        logger.info("--- ASSERT: User is returned ---");
        assertNotNull(result);
        
        logger.info("--- VERIFY: Verify repository.findById was called with ID 5L ---");
        verify(userRepository).findById(5L);
        
        logger.info("--- VERIFY: Repository.findById was NOT called with any other ID ---");
        verify(userRepository, never()).findById(1L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 4: Multiple consecutive calls to getUserById
     * 
     * Demonstrates mocking behavior across multiple method calls
     */
    @Test
    @DisplayName("Test 4: Multiple getUserById calls with different IDs")
    public void testMultipleGetUserByIdCalls() {
        logger.info("--- ARRANGE: Configure repository for multiple users ---");
        User user1 = new User(1L, "User One");
        User user2 = new User(2L, "User Two");
        User user3 = new User(3L, "User Three");
        
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user3));
        
        logger.info("--- ACT: Call service multiple times ---");
        User result1 = userService.getUserById(1L);
        User result2 = userService.getUserById(2L);
        User result3 = userService.getUserById(3L);
        
        logger.info("--- ASSERT: Verify all results ---");
        assertEquals("User One", result1.getName());
        assertEquals("User Two", result2.getName());
        assertEquals("User Three", result3.getName());
        
        logger.info("--- VERIFY: Repository findById was called 3 times total ---");
        verify(userRepository, times(3)).findById(org.mockito.ArgumentMatchers.anyLong());
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 5: Service properly handles repository exceptions
     * 
     * Demonstrates error handling in service layer
     */
    @Test
    @DisplayName("Test 5: Service handles repository returning null gracefully")
    public void testServiceHandlesRepositoryReturningNull() {
        logger.info("--- ARRANGE: Configure repository to return empty ---");
        when(userRepository.findById(10L)).thenReturn(Optional.empty());
        
        logger.info("--- ACT: Call service ---");
        User result = userService.getUserById(10L);
        
        logger.info("--- ASSERT: Service returns null gracefully ---");
        assertNull(result, "Service should return null for missing user");
        
        logger.info("--- VERIFY: No exceptions thrown ---");
        logger.info("✓ Test passed: Service handles missing data gracefully");
    }
}
