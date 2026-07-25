package com.cognizant.springtest;

import com.cognizant.springtest.controller.UserController;
import com.cognizant.springtest.entity.User;
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

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 10: Mocking a Service Dependency in a Controller Test
 * 
 * Task: Write a unit test for a Spring controller that uses a service to fetch data.
 * Mock the service dependency using Mockito.
 * 
 * This exercise demonstrates:
 * - How to mock service dependencies in controller tests
 * - Using MockMvc to test REST endpoints
 * - Verifying mock interactions
 * - Standalone MockMvc setup without full Spring context
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 10: Mocking a Service Dependency in a Controller Test")
public class Exercise10MockingServiceDependencyInControllerTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise10MockingServiceDependencyInControllerTest.class);
    
    private MockMvc mockMvc;
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MockMvc with mocked UserService ===");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        logger.info("✓ MockMvc initialized");
    }
    
    /**
     * Test 1: GET /users/{id} returns user when service returns data
     * 
     * ARRANGE: Mock the service to return a user
     * ACT: Send GET request to the controller
     * ASSERT: Verify response status and user data
     */
    @Test
    @DisplayName("Test 1: GET /users/{id} returns user when service returns user")
    public void testGetUserReturnUserWhenServiceReturnsUser() throws Exception {
        logger.info("--- ARRANGE: Create mock user and configure service mock ---");
        User mockUser = new User(1L, "John Doe");
        when(userService.getUserById(1L)).thenReturn(mockUser);
        
        logger.info("--- ACT: Send GET request to /users/1 ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
        
        logger.info("--- ASSERT: Verify service was called with correct argument ---");
        verify(userService).getUserById(1L);
        logger.info("✓ Test passed: Service mock was invoked correctly");
    }
    
    /**
     * Test 2: GET /users/{id} returns different user data on different calls
     * 
     * Demonstrates mocking with different return values for different IDs
     */
    @Test
    @DisplayName("Test 2: GET /users/{id} returns correct user for different IDs")
    public void testGetUserReturnsCorrectUserForDifferentIds() throws Exception {
        logger.info("--- ARRANGE: Configure service mock for multiple IDs ---");
        User user1 = new User(1L, "John Doe");
        User user2 = new User(2L, "Jane Smith");
        when(userService.getUserById(1L)).thenReturn(user1);
        when(userService.getUserById(2L)).thenReturn(user2);
        
        logger.info("--- ACT & ASSERT: Test user with ID 1 ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
        
        logger.info("--- ACT & ASSERT: Test user with ID 2 ---");
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Smith"));
        
        logger.info("--- VERIFY: Service was called twice with correct arguments ---");
        verify(userService).getUserById(1L);
        verify(userService).getUserById(2L);
        logger.info("✓ Test passed: Service correctly mocked for different IDs");
    }
    
    /**
     * Test 3: GET /users/{id} returns 404 when service returns null
     * 
     * Demonstrates handling of null responses from mocked service
     */
    @Test
    @DisplayName("Test 3: GET /users/{id} returns 404 when service returns null")
    public void testGetUserReturns404WhenServiceReturnsNull() throws Exception {
        logger.info("--- ARRANGE: Configure service mock to return null ---");
        when(userService.getUserById(99L)).thenReturn(null);
        
        logger.info("--- ACT & ASSERT: Send GET request and verify 404 status ---");
        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound());
        
        logger.info("--- VERIFY: Service was called ---");
        verify(userService).getUserById(99L);
        logger.info("✓ Test passed: 404 returned when user not found");
    }
    
    /**
     * Test 4: GET /users/{id} - Verify service interaction without checking response
     * 
     * Demonstrates the power of Mockito verify() for interaction testing
     */
    @Test
    @DisplayName("Test 4: Verify service interaction - getUserById called with correct ID")
    public void testVerifyServiceInteraction() throws Exception {
        logger.info("--- ARRANGE: Configure service mock ---");
        User mockUser = new User(5L, "Test User");
        when(userService.getUserById(5L)).thenReturn(mockUser);
        
        logger.info("--- ACT: Call the controller endpoint ---");
        mockMvc.perform(get("/users/5"))
                .andExpect(status().isOk());
        
        logger.info("--- ASSERT: Verify that service.getUserById(5L) was called exactly once ---");
        verify(userService).getUserById(5L);
        logger.info("✓ Test passed: Service interaction verified");
    }
}
