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
 * Exercise 3: Testing a REST Controller with MockMvc
 * 
 * Tests UserController GET endpoint
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 3: Testing a REST Controller with MockMvc")
public class Exercise3UserControllerTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise3UserControllerTest.class);
    
    private MockMvc mockMvc;
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MockMvc and mocked UserService ===");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    /**
     * Test GET /users/{id} endpoint when user exists.
     */
    @Test
    @DisplayName("Test 1: GET /users/{id} - User exists")
    public void testGetUserSuccess() throws Exception {
        logger.info("--- ARRANGE: Mock service to return a user ---");
        User mockUser = new User(1L, "John Doe");
        when(userService.getUserById(1L)).thenReturn(mockUser);
        
        logger.info("--- ACT: Send GET request to /users/1 ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
        
        logger.info("--- ASSERT: Verify response ---");
        verify(userService).getUserById(1L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test GET /users/{id} endpoint when user doesn't exist.
     */
    @Test
    @DisplayName("Test 2: GET /users/{id} - User not found")
    public void testGetUserNotFound() throws Exception {
        logger.info("--- ARRANGE: Mock service to return null ---");
        when(userService.getUserById(2L)).thenReturn(null);
        
        logger.info("--- ACT: Send GET request to /users/2 ---");
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isNotFound());
        
        logger.info("--- ASSERT: Verify 404 response ---");
        verify(userService).getUserById(2L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test GET /users/{id} with different users.
     */
    @Test
    @DisplayName("Test 3: GET /users/{id} - Multiple users")
    public void testGetMultipleUsers() throws Exception {
        logger.info("--- ARRANGE: Mock service for different users ---");
        User user1 = new User(1L, "Alice");
        User user2 = new User(2L, "Bob");
        when(userService.getUserById(1L)).thenReturn(user1);
        when(userService.getUserById(2L)).thenReturn(user2);
        
        logger.info("--- ACT: Send GET requests for different IDs ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
        
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
        
        logger.info("--- ASSERT: Verify both calls ---");
        verify(userService).getUserById(1L);
        verify(userService).getUserById(2L);
        logger.info("✓ Test passed");
    }
}
