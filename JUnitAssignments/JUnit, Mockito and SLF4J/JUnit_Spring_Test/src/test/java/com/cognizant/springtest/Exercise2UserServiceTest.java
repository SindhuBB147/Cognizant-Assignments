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
import static org.mockito.Mockito.*;

/**
 * Exercise 2: Mocking a Repository in a Service Test
 * 
 * Tests UserService with mocked UserRepository
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 2: Mocking a Repository in a Service Test")
public class Exercise2UserServiceTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise2UserServiceTest.class);
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize UserService with mocked UserRepository ===");
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }
    
    /**
     * Test getting user by ID when user exists.
     */
    @Test
    @DisplayName("Test 1: Get user by ID (user exists)")
    public void testGetUserById() {
        logger.info("--- ARRANGE: Mock repository to return a user ---");
        User mockUser = new User(1L, "John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        
        logger.info("--- ACT: Call userService.getUserById(1) ---");
        User result = userService.getUserById(1L);
        
        logger.info("--- ASSERT: Verify user data ---");
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        verify(userRepository).findById(1L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test getting user by ID when user doesn't exist.
     */
    @Test
    @DisplayName("Test 2: Get user by ID (user not found)")
    public void testGetUserByIdNotFound() {
        logger.info("--- ARRANGE: Mock repository to return empty ---");
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        
        logger.info("--- ACT: Call userService.getUserById(2) ---");
        User result = userService.getUserById(2L);
        
        logger.info("--- ASSERT: Verify result is null ---");
        assertNull(result);
        verify(userRepository).findById(2L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test getting multiple users.
     */
    @Test
    @DisplayName("Test 3: Get multiple users")
    public void testGetMultipleUsers() {
        logger.info("--- ARRANGE: Mock repository for multiple calls ---");
        User user1 = new User(1L, "User One");
        User user2 = new User(2L, "User Two");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        
        logger.info("--- ACT: Call userService.getUserById() twice ---");
        User result1 = userService.getUserById(1L);
        User result2 = userService.getUserById(2L);
        
        logger.info("--- ASSERT: Verify both users ---");
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals("User One", result1.getName());
        assertEquals("User Two", result2.getName());
        verify(userRepository, times(2)).findById(anyLong());
        logger.info("✓ Test passed");
    }
}
