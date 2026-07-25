package com.cognizant.mockitobasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Exercise 1: Mocking and Stubbing
 * 
 * Scenario: Test a service that depends on an external API using Mockito to mock
 * the external API and stub its methods.
 * 
 * Key Concepts:
 * - Creating mock objects with @Mock
 * - Stubbing methods with when().thenReturn()
 * - Injecting mocks into services for testing
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 1: Mocking and Stubbing")
public class Exercise1MockingAndStubbingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise1MockingAndStubbingTest.class);
    
    @Mock
    private ExternalApi mockApi;
    
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MyService with mocked ExternalApi ===");
        service = new MyService(mockApi);
    }
    
    /**
     * Test 1: Basic mocking and stubbing
     * - Mock the ExternalApi
     * - Stub getData() to return predefined value
     * - Verify the service returns the mocked data
     */
    @Test
    @DisplayName("Test 1: Mock ExternalApi and stub getData()")
    public void testExternalApiMocking() {
        logger.info("--- ARRANGE: Stubbing mockApi.getData() ---");
        when(mockApi.getData()).thenReturn("Mock Data");
        
        logger.info("--- ACT: Call service.fetchData() ---");
        String result = service.fetchData();
        
        logger.info("--- ASSERT: Verify result equals mocked data ---");
        assertEquals("Mock Data", result);
        logger.info("✓ Test passed: Got expected mocked data");
    }
    
    /**
     * Test 2: Multiple stub configurations
     * - Stub different methods with different return values
     */
    @Test
    @DisplayName("Test 2: Multiple stubs with different return values")
    public void testMultipleStubs() {
        logger.info("--- ARRANGE: Stub multiple methods ---");
        when(mockApi.getData()).thenReturn("Primary Data");
        when(mockApi.getDataById(1)).thenReturn("Data for ID 1");
        when(mockApi.getDataById(2)).thenReturn("Data for ID 2");
        
        logger.info("--- ACT: Call different service methods ---");
        String primaryData = service.fetchData();
        String dataId1 = service.fetchDataById(1);
        String dataId2 = service.fetchDataById(2);
        
        logger.info("--- ASSERT: Verify all results match stubbed values ---");
        assertEquals("Primary Data", primaryData);
        assertEquals("Data for ID 1", dataId1);
        assertEquals("Data for ID 2", dataId2);
        logger.info("✓ Test passed: All stubs returned expected values");
    }
    
    /**
     * Test 3: Stubbing with null return
     * - Stub method to return null
     * - Test service behavior with null
     */
    @Test
    @DisplayName("Test 3: Stub method to return null")
    public void testNullStubbing() {
        logger.info("--- ARRANGE: Stub getData() to return null ---");
        when(mockApi.getData()).thenReturn(null);
        
        logger.info("--- ACT: Call service.fetchData() ---");
        String result = service.fetchData();
        
        logger.info("--- ASSERT: Verify result is null ---");
        assertEquals(null, result);
        logger.info("✓ Test passed: Method correctly handled null response");
    }
    
    /**
     * Test 4: Stubbing with empty string
     * - Stub method to return empty string
     */
    @Test
    @DisplayName("Test 4: Stub method to return empty string")
    public void testEmptyStringStubbing() {
        logger.info("--- ARRANGE: Stub getData() to return empty string ---");
        when(mockApi.getData()).thenReturn("");
        
        logger.info("--- ACT: Call service.fetchData() ---");
        String result = service.fetchData();
        
        logger.info("--- ASSERT: Verify result is empty ---");
        assertEquals("", result);
        logger.info("✓ Test passed: Method correctly handled empty response");
    }
    
    /**
     * Test 5: Complex object stubbing
     * - Stub method with complex data scenarios
     */
    @Test
    @DisplayName("Test 5: Stub with complex data patterns")
    public void testComplexDataStubbing() {
        logger.info("--- ARRANGE: Stub getData() with complex JSON-like data ---");
        String complexData = "{\"status\": \"success\", \"data\": [1, 2, 3]}";
        when(mockApi.getData()).thenReturn(complexData);
        
        logger.info("--- ACT: Call service.fetchData() ---");
        String result = service.fetchData();
        
        logger.info("--- ASSERT: Verify result contains expected structure ---");
        assertEquals(complexData, result);
        logger.info("✓ Test passed: Complex data returned correctly");
    }
    
    /**
     * Test 6: Stubbing different parameter values
     * - Stub same method with different parameters returning different values
     */
    @Test
    @DisplayName("Test 6: Stub method with parameter-specific returns")
    public void testParameterSpecificStubbing() {
        logger.info("--- ARRANGE: Stub getDataById() with specific IDs ---");
        when(mockApi.getDataById(100)).thenReturn("Data for ID 100");
        when(mockApi.getDataById(200)).thenReturn("Data for ID 200");
        when(mockApi.getDataById(300)).thenReturn("Data for ID 300");
        
        logger.info("--- ACT: Fetch data for different IDs ---");
        String data100 = service.fetchDataById(100);
        String data200 = service.fetchDataById(200);
        String data300 = service.fetchDataById(300);
        
        logger.info("--- ASSERT: Verify each ID got correct data ---");
        assertEquals("Data for ID 100", data100);
        assertEquals("Data for ID 200", data200);
        assertEquals("Data for ID 300", data300);
        logger.info("✓ Test passed: Parameter-specific stubs worked correctly");
    }
}
