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
import static org.mockito.Mockito.*;

/**
 * Exercise 5: Mocking and Stubbing with Multiple Returns
 * 
 * Scenario: Test a service that depends on an external API with multiple
 * consecutive return values on repeated calls.
 * 
 * Key Concepts:
 * - Using thenReturn() with multiple values for consecutive calls
 * - Using thenReturn() chaining for sequential returns
 * - Testing behavior with different return values on each call
 * - Handling variable return patterns
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 5: Mocking with Multiple Returns")
public class Exercise5MultipleReturnsTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise5MultipleReturnsTest.class);
    
    @Mock
    private ExternalApi mockApi;
    
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MyService with mocked ExternalApi ===");
        service = new MyService(mockApi);
    }
    
    /**
     * Test 1: Consecutive calls with different return values
     * - Setup mock to return different values on each call
     * - First call returns value1, second returns value2, etc.
     */
    @Test
    @DisplayName("Test 1: Consecutive calls with different returns")
    public void testConsecutiveReturns() {
        logger.info("--- ARRANGE: Setup mock to return different values ---");
        when(mockApi.getData())
            .thenReturn("First Call")
            .thenReturn("Second Call")
            .thenReturn("Third Call");
        
        logger.info("--- ACT: Make consecutive calls to service ---");
        String result1 = service.fetchData();
        String result2 = service.fetchData();
        String result3 = service.fetchData();
        
        logger.info("--- ASSERT: Verify each call got different return value ---");
        assertEquals("First Call", result1);
        assertEquals("Second Call", result2);
        assertEquals("Third Call", result3);
        logger.info("✓ Test passed: Consecutive returns worked correctly");
    }
    
    /**
     * Test 2: Last value is reused for additional calls
     * - Setup with multiple returns, then final call repeats last value
     */
    @Test
    @DisplayName("Test 2: Last return value reused for extra calls")
    public void testLastReturnRepeats() {
        logger.info("--- ARRANGE: Setup mock with limited returns ---");
        when(mockApi.getData())
            .thenReturn("First")
            .thenReturn("Second");
        
        logger.info("--- ACT: Make three calls (more than configured) ---");
        String result1 = service.fetchData();
        String result2 = service.fetchData();
        String result3 = service.fetchData();  // Should return "Second" again
        
        logger.info("--- ASSERT: Verify last value repeats ---");
        assertEquals("First", result1);
        assertEquals("Second", result2);
        assertEquals("Second", result3);  // Last value repeats
        logger.info("✓ Test passed: Last return value repeated as expected");
    }
    
    /**
     * Test 3: Multiple ID-specific returns
     * - Different IDs return different values on consecutive calls
     */
    @Test
    @DisplayName("Test 3: Multiple returns for different IDs")
    public void testMultipleReturnsForDifferentIds() {
        logger.info("--- ARRANGE: Setup mock for ID-specific returns ---");
        when(mockApi.getDataById(1))
            .thenReturn("ID1-First")
            .thenReturn("ID1-Second");
        
        when(mockApi.getDataById(2))
            .thenReturn("ID2-First")
            .thenReturn("ID2-Second");
        
        logger.info("--- ACT: Fetch data for different IDs ---");
        String id1First = service.fetchDataById(1);
        String id2First = service.fetchDataById(2);
        String id1Second = service.fetchDataById(1);
        String id2Second = service.fetchDataById(2);
        
        logger.info("--- ASSERT: Verify returns per ID ---");
        assertEquals("ID1-First", id1First);
        assertEquals("ID2-First", id2First);
        assertEquals("ID1-Second", id1Second);
        assertEquals("ID2-Second", id2Second);
        logger.info("✓ Test passed: ID-specific multiple returns worked");
    }
    
    /**
     * Test 4: Multiple returns with null value
     * - Include null as one of the return values
     */
    @Test
    @DisplayName("Test 4: Multiple returns including null")
    public void testMultipleReturnsWithNull() {
        logger.info("--- ARRANGE: Setup mock with null in sequence ---");
        when(mockApi.getData())
            .thenReturn("Valid Data")
            .thenReturn(null)
            .thenReturn("More Data");
        
        logger.info("--- ACT: Call service multiple times ---");
        String result1 = service.fetchData();
        String result2 = service.fetchData();
        String result3 = service.fetchData();
        
        logger.info("--- ASSERT: Verify sequence including null ---");
        assertEquals("Valid Data", result1);
        assertEquals(null, result2);
        assertEquals("More Data", result3);
        logger.info("✓ Test passed: Multiple returns with null handled");
    }
    
    /**
     * Test 5: Long sequence of returns
     * - Setup many consecutive return values
     */
    @Test
    @DisplayName("Test 5: Many consecutive returns")
    public void testManyConsecutiveReturns() {
        logger.info("--- ARRANGE: Setup mock with many consecutive returns ---");
        when(mockApi.getData())
            .thenReturn("Value1")
            .thenReturn("Value2")
            .thenReturn("Value3")
            .thenReturn("Value4")
            .thenReturn("Value5");
        
        logger.info("--- ACT: Make multiple calls ---");
        String[] results = new String[5];
        for (int i = 0; i < 5; i++) {
            results[i] = service.fetchData();
            logger.info("Call {} returned: {}", i + 1, results[i]);
        }
        
        logger.info("--- ASSERT: Verify all values returned in order ---");
        assertEquals("Value1", results[0]);
        assertEquals("Value2", results[1]);
        assertEquals("Value3", results[2]);
        assertEquals("Value4", results[3]);
        assertEquals("Value5", results[4]);
        logger.info("✓ Test passed: Many consecutive returns worked");
    }
    
    /**
     * Test 6: Empty string returns mixed with valid data
     */
    @Test
    @DisplayName("Test 6: Mixed empty and valid returns")
    public void testMixedEmptyAndValidReturns() {
        logger.info("--- ARRANGE: Setup mock with empty string in sequence ---");
        when(mockApi.getData())
            .thenReturn("Data")
            .thenReturn("")
            .thenReturn("More Data");
        
        logger.info("--- ACT: Call service multiple times ---");
        String result1 = service.fetchData();
        String result2 = service.fetchData();
        String result3 = service.fetchData();
        
        logger.info("--- ASSERT: Verify mixed returns ---");
        assertEquals("Data", result1);
        assertEquals("", result2);
        assertEquals("More Data", result3);
        logger.info("✓ Test passed: Mixed empty and valid returns handled");
    }
    
    /**
     * Test 7: Multiple returns for fetchDataMultipleTimes()
     * - This method internally makes 3 calls, verify correct sequence
     */
    @Test
    @DisplayName("Test 7: Multiple returns for sequential internal calls")
    public void testMultipleReturnsInternalCalls() {
        logger.info("--- ARRANGE: Setup mock for 3 consecutive calls ---");
        when(mockApi.getData())
            .thenReturn("First")
            .thenReturn("Second")
            .thenReturn("Third");
        
        logger.info("--- ACT: Call service method that makes 3 internal calls ---");
        String[] results = service.fetchDataMultipleTimes();
        
        logger.info("--- ASSERT: Verify all 3 internal calls got correct values ---");
        assertEquals("First", results[0]);
        assertEquals("Second", results[1]);
        assertEquals("Third", results[2]);
        logger.info("✓ Test passed: Internal sequential calls got multiple returns");
    }
    
    /**
     * Test 8: Returns alternate between patterns
     */
    @Test
    @DisplayName("Test 8: Alternating pattern of returns")
    public void testAlternatingReturns() {
        logger.info("--- ARRANGE: Setup alternating return pattern ---");
        when(mockApi.getData())
            .thenReturn("Start")
            .thenReturn("Middle")
            .thenReturn("End")
            .thenReturn("Middle")
            .thenReturn("Start");
        
        logger.info("--- ACT: Make 5 calls ---");
        String r1 = service.fetchData();
        String r2 = service.fetchData();
        String r3 = service.fetchData();
        String r4 = service.fetchData();
        String r5 = service.fetchData();
        
        logger.info("--- ASSERT: Verify alternating pattern ---");
        assertEquals("Start", r1);
        assertEquals("Middle", r2);
        assertEquals("End", r3);
        assertEquals("Middle", r4);
        assertEquals("Start", r5);
        logger.info("✓ Test passed: Alternating return pattern worked");
    }
    
    /**
     * Test 9: Complex multi-call scenario
     */
    @Test
    @DisplayName("Test 9: Complex multi-method multiple returns")
    public void testComplexMultiMethodReturns() {
        logger.info("--- ARRANGE: Setup multiple methods with multiple returns ---");
        when(mockApi.getData())
            .thenReturn("Data1")
            .thenReturn("Data2");
        
        when(mockApi.getDataById(1))
            .thenReturn("ID1-A")
            .thenReturn("ID1-B");
        
        logger.info("--- ACT: Call different methods multiple times ---");
        String d1 = service.fetchData();
        String id1a = service.fetchDataById(1);
        String d2 = service.fetchData();
        String id1b = service.fetchDataById(1);
        
        logger.info("--- ASSERT: Verify each method returned correctly ---");
        assertEquals("Data1", d1);
        assertEquals("ID1-A", id1a);
        assertEquals("Data2", d2);
        assertEquals("ID1-B", id1b);
        logger.info("✓ Test passed: Complex multi-method scenario worked");
    }
}
