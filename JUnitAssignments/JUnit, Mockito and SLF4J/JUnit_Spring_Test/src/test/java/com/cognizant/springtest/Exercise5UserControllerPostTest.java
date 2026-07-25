package com.cognizant.springtest;

import com.cognizant.springtest.controller.UserController;
import com.cognizant.springtest.entity.User;
import com.cognizant.springtest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 5: Test Controller POST Endpoint
 * 
 * Tests UserController POST and PUT endpoints
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 5: Test Controller POST Endpoint")
public class Exercise5UserControllerPostTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise5UserControllerPostTest.class);
    
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MockMvc and mocked UserService ===");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }
    
    /**
     * Test POST /users to create a new user.
     */
    @Test
    @DisplayName("Test 1: POST /users - Create user")
    public void testCreateUser() throws Exception {
        logger.info("--- ARRANGE: Prepare user data ---");
        User newUser = new User("John Doe");
        User savedUser = new User(1L, "John Doe");
        when(userService.saveUser(newUser)).thenReturn(savedUser);
        
        logger.info("--- ACT: Send POST request to /users ---");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
        
        logger.info("--- ASSERT: Verify service was called ---");
        verify(userService).saveUser(newUser);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test POST /users with multiple users.
     */
    @Test
    @DisplayName("Test 2: POST /users - Create multiple users")
    public void testCreateMultipleUsers() throws Exception {
        logger.info("--- ARRANGE: Prepare multiple users ---");
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User savedUser1 = new User(1L, "Alice");
        User savedUser2 = new User(2L, "Bob");
        when(userService.saveUser(user1)).thenReturn(savedUser1);
        when(userService.saveUser(user2)).thenReturn(savedUser2);
        
        logger.info("--- ACT: POST first user ---");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk());
        
        logger.info("--- ACT: POST second user ---");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk());
        
        logger.info("--- ASSERT: Verify both saves ---");
        verify(userService).saveUser(user1);
        verify(userService).saveUser(user2);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test PUT /users/{id} to update a user.
     */
    @Test
    @DisplayName("Test 3: PUT /users/{id} - Update user")
    public void testUpdateUser() throws Exception {
        logger.info("--- ARRANGE: Prepare updated user data ---");
        User updateData = new User("Updated Name");
        User updatedUser = new User(1L, "Updated Name");
        when(userService.updateUser(1L, updateData)).thenReturn(updatedUser);
        
        logger.info("--- ACT: Send PUT request to /users/1 ---");
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Name"));
        
        logger.info("--- ASSERT: Verify service was called ---");
        verify(userService).updateUser(1L, updateData);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test POST with empty body.
     */
    @Test
    @DisplayName("Test 4: POST /users - Create with empty name")
    public void testCreateUserEmptyName() throws Exception {
        logger.info("--- ARRANGE: Create user with null name ---");
        User newUser = new User();
        User savedUser = new User(1L, null);
        when(userService.saveUser(newUser)).thenReturn(savedUser);
        
        logger.info("--- ACT: Send POST request ---");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk());
        
        logger.info("--- ASSERT: Verify save was called ---");
        verify(userService).saveUser(newUser);
        logger.info("✓ Test passed");
    }
}
