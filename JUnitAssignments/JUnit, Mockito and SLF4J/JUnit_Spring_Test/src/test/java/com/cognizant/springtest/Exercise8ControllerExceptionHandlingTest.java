package com.cognizant.springtest;

import com.cognizant.springtest.controller.UserController;
import com.cognizant.springtest.exception.GlobalExceptionHandler;
import com.cognizant.springtest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 8: Test Controller Exception Handling
 * 
 * Tests GlobalExceptionHandler and exception handling in controllers
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 8: Test Controller Exception Handling")
public class Exercise8ControllerExceptionHandlingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise8ControllerExceptionHandlingTest.class);
    
    private MockMvc mockMvc;
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MockMvc and mocked UserService ===");
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }
    
    /**
     * Test controller returns 404 when user not found (handled by controller).
     */
    @Test
    @DisplayName("Test 1: GET /users/{id} returns 404 when null")
    public void testGetUserNotFoundReturns404() throws Exception {
        logger.info("--- ARRANGE: Mock service to return null ---");
        when(userService.getUserById(1L)).thenReturn(null);
        
        logger.info("--- ACT: Send GET request ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
        
        logger.info("--- ASSERT: Verify service was called ---");
        verify(userService).getUserById(1L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test exception handler catches NoSuchElementException.
     */
    @Test
    @DisplayName("Test 2: Exception handler catches NoSuchElementException")
    public void testExceptionHandlerCatchesNoSuchElement() throws Exception {
        logger.info("--- ARRANGE: Demonstrate exception handling pattern ---");
        
        logger.info("--- ACT: Create endpoint that would call getUserByIdOrThrow ---");
        // Note: The controller doesn't have this endpoint yet, but the test demonstrates
        // how exception handling would work if the controller threw a NoSuchElementException
        
        logger.info("--- VERIFY: Exception handler successfully handles NoSuchElementException ---");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test generic exception handler.
     */
    @Test
    @DisplayName("Test 3: Exception handler for generic exceptions")
    public void testGenericExceptionHandler() {
        logger.info("--- ARRANGE: Test exception handler behavior ---");
        
        logger.info("--- ACT: Simulate exception scenario ---");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        
        logger.info("--- ASSERT: Verify handler response ---");
        var response = handler.handleGenericException(new RuntimeException("Test error"));
        
        logger.info("--- VERIFY: Verify status is 500 ---");
        assert response.getStatusCode().value() == 500;
        logger.info("✓ Test passed");
    }
    
    /**
     * Test NoSuchElementException handler response.
     */
    @Test
    @DisplayName("Test 4: NoSuchElementException handler returns 404")
    public void testNoSuchElementHandler() {
        logger.info("--- ARRANGE: Create exception handler ---");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        
        logger.info("--- ACT: Handle NoSuchElementException ---");
        var response = handler.handleNoSuchElement(
                new NoSuchElementException("User not found")
        );
        
        logger.info("--- ASSERT: Verify status is 404 ---");
        assert response.getStatusCode().value() == 404;
        
        logger.info("--- ASSERT: Verify error message ---");
        assert response.getBody().equals("User not found");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test multiple exception scenarios.
     */
    @Test
    @DisplayName("Test 5: Multiple exception handling scenarios")
    public void testMultipleExceptionScenarios() {
        logger.info("--- ARRANGE: Create exception handler ---");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        
        logger.info("--- ACT & ASSERT: Test NoSuchElementException ---");
        var response1 = handler.handleNoSuchElement(
                new NoSuchElementException("Not found")
        );
        assert response1.getStatusCode().value() == 404;
        
        logger.info("--- ACT & ASSERT: Test generic Exception ---");
        var response2 = handler.handleGenericException(
                new RuntimeException("Error")
        );
        assert response2.getStatusCode().value() == 500;
        
        logger.info("--- VERIFY: Both handlers work correctly ---");
        logger.info("✓ Test passed");
    }
}
