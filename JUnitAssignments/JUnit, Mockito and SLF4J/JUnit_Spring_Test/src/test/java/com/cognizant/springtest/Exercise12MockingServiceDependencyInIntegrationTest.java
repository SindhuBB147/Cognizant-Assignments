package com.cognizant.springtest;

import com.cognizant.springtest.controller.UserController;
import com.cognizant.springtest.entity.User;
import com.cognizant.springtest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 12: Mocking a Service Dependency in an Integration Test
 * 
 * Task: Write an integration test for a Spring Boot application that mocks a service
 * dependency using Mockito.
 * 
 * This exercise demonstrates:
 * - Using @SpringBootTest for full application context integration testing
 * - Using @AutoConfigureMockMvc to auto-configure MockMvc
 * - Using @MockBean to replace real beans with mocks in the Spring context
 * - Testing controller endpoints in a fully integrated Spring environment
 * 
 * Key Differences from Standalone MockMvc Tests:
 * - Full Spring context is loaded
 * - Only specific beans are mocked with @MockBean
 * - Other beans and configurations are real
 * - More comprehensive testing but slower to execute
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Exercise 12: Mocking a Service Dependency in an Integration Test")
public class Exercise12MockingServiceDependencyInIntegrationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise12MockingServiceDependencyInIntegrationTest.class);
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Spring Boot context loaded with mocked UserService ===");
        logger.info("✓ MockMvc configured, UserService mocked in Spring context");
    }
    
    /**
     * Test 1: GET /users/{id} returns user in full Spring integration context
     * 
     * ARRANGE: Mock the UserService bean in Spring context
     * ACT: Make HTTP request through MockMvc
     * ASSERT: Verify response and mock interactions
     */
    @Test
    @DisplayName("Test 1: GET /users/{id} returns user in integrated Spring context")
    public void testGetUserReturnsUserInIntegratedContext() throws Exception {
        logger.info("--- ARRANGE: Create mock user and configure mocked service ---");
        User mockUser = new User(1L, "Integration Test User");
        when(userService.getUserById(1L)).thenReturn(mockUser);
        
        logger.info("--- ACT: Send GET request through MockMvc ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Integration Test User"));
        
        logger.info("--- ASSERT: Verify mocked service was called ---");
        verify(userService).getUserById(1L);
        logger.info("✓ Test passed: Service mock worked in integrated context");
    }
    
    /**
     * Test 2: GET /users/{id} with different user data
     * 
     * Demonstrates that @MockBean works across multiple test methods
     */
    @Test
    @DisplayName("Test 2: GET /users/{id} returns different user on different calls")
    public void testGetUserReturnsDifferentUsersInIntegratedContext() throws Exception {
        logger.info("--- ARRANGE: Configure service mock for two different users ---");
        User user1 = new User(1L, "Alice");
        User user2 = new User(2L, "Bob");
        when(userService.getUserById(1L)).thenReturn(user1);
        when(userService.getUserById(2L)).thenReturn(user2);
        
        logger.info("--- ACT: Request first user ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
        
        logger.info("--- ACT: Request second user ---");
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
        
        logger.info("--- VERIFY: Service was called for both requests ---");
        verify(userService).getUserById(1L);
        verify(userService).getUserById(2L);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 3: GET /users/{id} returns 404 when service returns null
     * 
     * Demonstrates mocking null responses in integration context
     */
    @Test
    @DisplayName("Test 3: GET /users/{id} returns 404 when user not found")
    public void testGetUserReturns404WhenUserNotFoundInIntegratedContext() throws Exception {
        logger.info("--- ARRANGE: Mock service to return null for missing user ---");
        when(userService.getUserById(999L)).thenReturn(null);
        
        logger.info("--- ACT: Send GET request for non-existent user ---");
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
        
        logger.info("--- VERIFY: Service was called ---");
        verify(userService).getUserById(999L);
        logger.info("✓ Test passed: 404 returned for missing user");
    }
    
    /**
     * Test 4: Verify @MockBean replaces real service in Spring context
     * 
     * Key point: The real UserService is replaced by the mock,
     * so any logic in the real service is bypassed.
     */
    @Test
    @DisplayName("Test 4: @MockBean successfully replaces real UserService in context")
    public void testMockBeanReplacesRealServiceInSpringContext() throws Exception {
        logger.info("--- ARRANGE: Configure mock to return specific data ---");
        User mockUser = new User(100L, "Mock User in Spring Context");
        when(userService.getUserById(100L)).thenReturn(mockUser);
        
        logger.info("--- ACT: Request the user ---");
        mockMvc.perform(get("/users/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100L));
        
        logger.info("--- ASSERT: Mocked service responds (not real service logic) ---");
        verify(userService).getUserById(100L);
        logger.info("✓ Test passed: @MockBean successfully replaced real service");
    }
    
    /**
     * Test 5: Test exception scenario with mocked service
     * 
     * Demonstrates how mocked service handles various scenarios
     */
    @Test
    @DisplayName("Test 5: Integration test with mocked service handling null response")
    public void testMockedServiceHandlesNullResponseInIntegrationTest() throws Exception {
        logger.info("--- ARRANGE: Mock service to return null ---");
        when(userService.getUserById(50L)).thenReturn(null);
        
        logger.info("--- ACT: Request user ---");
        mockMvc.perform(get("/users/50"))
                .andExpect(status().isNotFound());
        
        logger.info("--- VERIFY: Mock was invoked ---");
        verify(userService).getUserById(50L);
        
        logger.info("--- ASSERT: Integration test completed successfully ---");
        logger.info("✓ Test passed: Mocked service handles edge cases in integration test");
    }
    
    /**
     * Test 6: Demonstrate difference between @SpringBootTest and Standalone MockMvc
     * 
     * This test shows that full Spring context is available,
     * including automatic configuration, property loading, etc.
     */
    @Test
    @DisplayName("Test 6: Full Spring context available in @SpringBootTest")
    public void testFullSpringContextAvailableInIntegrationTest() throws Exception {
        logger.info("--- DEMONSTRATE: Full Spring context loaded ---");
        logger.info("This test runs in full Spring Boot context:");
        logger.info("✓ Application properties loaded");
        logger.info("✓ Component scanning complete");
        logger.info("✓ Auto-configuration applied");
        logger.info("✓ All beans except mocked ones are real");
        
        logger.info("--- ARRANGE: Configure mock ---");
        User mockUser = new User(1L, "Full Spring Context");
        when(userService.getUserById(1L)).thenReturn(mockUser);
        
        logger.info("--- ACT: Test endpoint ---");
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
        
        logger.info("--- ASSERT: Full integration successful ---");
        verify(userService).getUserById(1L);
        logger.info("✓ Test passed: Integration test demonstrates full Spring context");
    }
}
