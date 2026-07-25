package com.cognizant.springtest;

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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exercise 6: Test Service Exception Handling
 * 
 * Tests UserService exception handling when user is not found
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 6: Test Service Exception Handling")
public class Exercise6ExceptionHandlingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise6ExceptionHandlingTest.class);
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize UserService with mocked repository ===");
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }
    
    /**
     * Test that getUserByIdOrThrow throws exception when user not found.
     */
    @Test
    @DisplayName("Test 1: getUserByIdOrThrow throws NoSuchElementException")
    public void testGetUserByIdOrThrowNotFound() {
        logger.info("--- ARRANGE: Mock repository to return empty ---");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        
        logger.info("--- ACT & ASSERT: Verify exception is thrown ---");
        assertThrows(NoSuchElementException.class, () -> {
            userService.getUserByIdOrThrow(1L);
        });
        
        logger.info("--- VERIFY: Repository was called ---");
        verify(userRepository).findById(1L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test that getUserByIdOrThrow returns user when found.
     */
    @Test
    @DisplayName("Test 2: getUserByIdOrThrow returns user when found")
    public void testGetUserByIdOrThrowSuccess() {
        logger.info("--- ARRANGE: Mock repository to return a user ---");
        var mockUser = new com.cognizant.springtest.entity.User(1L, "Test User");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        
        logger.info("--- ACT: Call getUserByIdOrThrow(1) ---");
        var result = userService.getUserByIdOrThrow(1L);
        
        logger.info("--- ASSERT: Verify user returned ---");
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test User", result.getName());
        verify(userRepository).findById(1L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test exception message includes user ID.
     */
    @Test
    @DisplayName("Test 3: Exception message includes user ID")
    public void testExceptionMessageIncludesId() {
        logger.info("--- ARRANGE: Mock repository ---");
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        
        logger.info("--- ACT & ASSERT: Verify exception message ---");
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getUserByIdOrThrow(99L);
        });
        
        assertTrue(exception.getMessage().contains("99"));
        logger.info("--- VERIFY: Exception message contains ID: {} ---", exception.getMessage());
        logger.info("✓ Test passed");
    }
    
    /**
     * Test multiple calls with some failing.
     */
    @Test
    @DisplayName("Test 4: Multiple calls with mixed success/failure")
    public void testMultipleCalls() {
        logger.info("--- ARRANGE: Mock repository ---");
        var user1 = new com.cognizant.springtest.entity.User(1L, "User 1");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        
        logger.info("--- ACT: First call succeeds ---");
        var result1 = userService.getUserByIdOrThrow(1L);
        assertNotNull(result1);
        
        logger.info("--- ACT & ASSERT: Second call fails ---");
        assertThrows(NoSuchElementException.class, () -> {
            userService.getUserByIdOrThrow(2L);
        });
        
        logger.info("--- VERIFY: Both calls were made ---");
        verify(userRepository).findById(1L);
        verify(userRepository).findById(2L);
        logger.info("✓ Test passed");
    }
}
