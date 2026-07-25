package com.cognizant.mockitoadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exercise 2: Mocking External Services (RESTful APIs)
 * 
 * Tests a service that calls external RESTful APIs.
 * Demonstrates mocking of HTTP clients and API patterns.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 2: Mocking External Services (RESTful APIs)")
public class Exercise2RestClientMockingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise2RestClientMockingTest.class);
    
    @Mock
    private RestClient mockRestClient;
    
    private ApiService apiService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize ApiService with mocked RestClient ===");
        apiService = new ApiService(mockRestClient);
    }
    
    /**
     * Test 1: Basic REST API mocking
     */
    @Test
    @DisplayName("Test 1: Mock REST client and fetch data")
    public void testServiceWithMockRestClient() {
        logger.info("--- ARRANGE: Stub REST client ---");
        when(mockRestClient.getResponse()).thenReturn("Mock Response");
        
        logger.info("--- ACT: Call apiService.fetchData() ---");
        String result = apiService.fetchData();
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Fetched Mock Response", result);
        verify(mockRestClient).getResponse();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 2: GET request with ID
     */
    @Test
    @DisplayName("Test 2: Fetch data by ID from REST API")
    public void testFetchDataById() {
        logger.info("--- ARRANGE: Stub REST client ---");
        when(mockRestClient.getResponseById(123)).thenReturn("User Data");
        
        logger.info("--- ACT: Call apiService.fetchDataById(123) ---");
        String result = apiService.fetchDataById(123);
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Fetched User Data", result);
        verify(mockRestClient).getResponseById(123);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 3: POST request
     */
    @Test
    @DisplayName("Test 3: Post data to REST API")
    public void testPostData() {
        logger.info("--- ARRANGE: Stub REST client ---");
        when(mockRestClient.postData("New Data")).thenReturn("Created Successfully");
        
        logger.info("--- ACT: Call apiService.postDataToApi() ---");
        String result = apiService.postDataToApi("New Data");
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Posted Created Successfully", result);
        verify(mockRestClient).postData("New Data");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 4: PUT request
     */
    @Test
    @DisplayName("Test 4: Update data via REST API")
    public void testUpdateData() {
        logger.info("--- ARRANGE: Stub REST client ---");
        when(mockRestClient.putData(1, "Updated Data")).thenReturn("Updated Successfully");
        
        logger.info("--- ACT: Call apiService.updateData() ---");
        String result = apiService.updateData(1, "Updated Data");
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Updated Updated Successfully", result);
        verify(mockRestClient).putData(1, "Updated Data");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 5: DELETE request
     */
    @Test
    @DisplayName("Test 5: Delete resource via REST API")
    public void testDeleteResource() {
        logger.info("--- ARRANGE: Stub REST client ---");
        when(mockRestClient.deleteResource(1)).thenReturn("Deleted Successfully");
        
        logger.info("--- ACT: Call apiService.deleteResource() ---");
        String result = apiService.deleteResource(1);
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Deleted Deleted Successfully", result);
        verify(mockRestClient).deleteResource(1);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 6: Multiple API calls
     */
    @Test
    @DisplayName("Test 6: Multiple REST API calls")
    public void testMultipleApiCalls() {
        logger.info("--- ARRANGE: Setup multiple stubs ---");
        when(mockRestClient.getResponse()).thenReturn("Data");
        when(mockRestClient.getResponseById(1)).thenReturn("Resource");
        when(mockRestClient.postData(anyString())).thenReturn("Posted");
        
        logger.info("--- ACT: Call multiple API methods ---");
        apiService.fetchData();
        apiService.fetchDataById(1);
        apiService.postDataToApi("test");
        
        logger.info("--- ASSERT: Verify all calls ---");
        verify(mockRestClient).getResponse();
        verify(mockRestClient).getResponseById(1);
        verify(mockRestClient).postData("test");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 7: Null API response
     */
    @Test
    @DisplayName("Test 7: Handle null API response")
    public void testNullApiResponse() {
        logger.info("--- ARRANGE: Stub REST client to return null ---");
        when(mockRestClient.getResponse()).thenReturn(null);
        
        logger.info("--- ACT: Call apiService.fetchData() ---");
        String result = apiService.fetchData();
        
        logger.info("--- ASSERT: Verify null handling ---");
        assertEquals("No response", result);
        logger.info("✓ Test passed");
    }
}
