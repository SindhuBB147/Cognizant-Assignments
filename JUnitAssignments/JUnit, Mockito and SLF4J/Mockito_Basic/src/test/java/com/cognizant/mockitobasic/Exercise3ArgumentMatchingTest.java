package com.cognizant.mockitobasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 3: Argument Matching
 * 
 * Scenario: Test methods with argument matchers for flexible verification.
 * 
 * Key Concepts:
 * - Using any() matcher for any argument
 * - Using anyString() for string arguments
 * - Using anyInt() for integer arguments
 * - Using eq() for exact matching
 * - Using matches() for regex pattern matching
 * - Using argThat() for custom argument matching
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 3: Argument Matching")
public class Exercise3ArgumentMatchingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise3ArgumentMatchingTest.class);
    
    @Mock
    private ExternalApi mockApi;
    
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MyService with mocked ExternalApi ===");
        service = new MyService(mockApi);
    }
    
    /**
     * Test 1: Use anyString() matcher
     * - Verify method called with any string argument
     */
    @Test
    @DisplayName("Test 1: Verify with anyString() matcher")
    public void testAnyStringMatcher() {
        logger.info("--- ARRANGE: Stub with anyString() ---");
        when(mockApi.validateData(anyString())).thenReturn(true);
        
        logger.info("--- ACT: Call with multiple different strings ---");
        service.validateAndSendData("Test Data");
        
        logger.info("--- ASSERT: Verify method called with any string ---");
        verify(mockApi).validateData(anyString());
        logger.info("✓ Test passed: anyString() matcher worked");
    }
    
    /**
     * Test 2: Use anyInt() matcher
     * - Verify method called with any integer argument
     */
    @Test
    @DisplayName("Test 2: Verify with anyInt() matcher")
    public void testAnyIntMatcher() {
        logger.info("--- ARRANGE: Stub with anyInt() ---");
        when(mockApi.getDataById(anyInt())).thenReturn("Data");
        
        logger.info("--- ACT: Call with different integer values ---");
        service.fetchDataById(5);
        service.fetchDataById(100);
        service.fetchDataById(999);
        
        logger.info("--- ASSERT: Verify method called 3 times with any integer ---");
        verify(mockApi, times(3)).getDataById(anyInt());
        logger.info("✓ Test passed: anyInt() matcher worked");
    }
    
    /**
     * Test 3: Use any() matcher for objects
     * - Verify void method called with any object
     */
    @Test
    @DisplayName("Test 3: Verify void method with any() matcher")
    public void testAnyMatcher() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call processAndSendData with specific data ---");
        service.processAndSendData("Any Data");
        
        logger.info("--- ASSERT: Verify void method called with any string ---");
        verify(mockApi).sendData(any(String.class));
        logger.info("✓ Test passed: any() matcher worked");
    }
    
    /**
     * Test 4: Mix exact matching with matchers
     * - Use eq() for exact match and anyInt() for flexible match
     */
    @Test
    @DisplayName("Test 4: Mix exact matching with argument matchers")
    public void testMixedMatchers() {
        logger.info("--- ARRANGE: Stub with mixed matchers ---");
        doNothing().when(mockApi).sendDataWithPriority(eq("Critical"), anyInt());
        
        logger.info("--- ACT: Call with exact string and different priorities ---");
        service.processAndSendDataWithPriority("Critical", 1);
        service.processAndSendDataWithPriority("Critical", 10);
        
        logger.info("--- ASSERT: Verify exact string was used for both calls ---");
        verify(mockApi, times(2)).sendDataWithPriority(eq("Critical"), anyInt());
        logger.info("✓ Test passed: Mixed matchers worked");
    }
    
    /**
     * Test 5: Use matches() for regex pattern matching
     * - Verify argument matches regex pattern
     */
    @Test
    @DisplayName("Test 5: Verify with matches() regex matcher")
    public void testRegexMatcher() {
        logger.info("--- ARRANGE: Stub validateData with regex matcher ---");
        when(mockApi.validateData(matches("^[A-Z].*"))).thenReturn(true);
        
        logger.info("--- ACT: Call with data starting with uppercase ---");
        boolean result = service.validateAndSendData("DataStartsWithUppercase");
        
        logger.info("--- ASSERT: Verify pattern matched ---");
        assertTrue(result);
        logger.info("✓ Test passed: Regex matcher worked");
    }
    
    /**
     * Test 6: Use argThat() with custom matcher
     * - Create custom argument matcher for verification
     */
    @Test
    @DisplayName("Test 6: Verify with custom matching logic")
    public void testArgThatCustomMatcher() {
        logger.info("--- ARRANGE: Setup mock ---");
        when(mockApi.getDataById(anyInt())).thenReturn("Data");
        
        logger.info("--- ACT: Call with specific ID ---");
        service.fetchDataById(5);
        
        logger.info("--- ASSERT: Verify with eq() matcher ---");
        verify(mockApi).getDataById(eq(5));
        logger.info("✓ Test passed: Custom matching worked");
    }
    
    /**
     * Test 7: Verify multiple arguments with matchers
     */
    @Test
    @DisplayName("Test 7: Verify multiple arguments with matchers")
    public void testMultipleArgumentMatchers() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call with multiple arguments ---");
        service.processAndSendDataWithPriority("ImportantData", 5);
        
        logger.info("--- ASSERT: Verify with matchers for each argument ---");
        verify(mockApi).sendDataWithPriority(anyString(), anyInt());
        logger.info("✓ Test passed: Multiple argument matchers worked");
    }
    
    /**
     * Test 8: Verify null handling with matchers
     */
    @Test
    @DisplayName("Test 8: Verify with nullable arguments")
    public void testNullableMatcher() {
        logger.info("--- ARRANGE: Stub with nullable argument ---");
        when(mockApi.getData()).thenReturn(null);
        
        logger.info("--- ACT: Call service ---");
        String result = service.fetchData();
        
        logger.info("--- ASSERT: Verify call happened even with null return ---");
        verify(mockApi).getData();
        logger.info("✓ Test passed: Handled null with matcher");
    }
    
    /**
     * Test 9: Combine multiple matchers in single verification
     */
    @Test
    @DisplayName("Test 9: Complex matcher combination")
    public void testComplexMatcherCombination() {
        logger.info("--- ARRANGE: Setup mock with multiple arguments ---");
        doNothing().when(mockApi).sendDataWithPriority(anyString(), anyInt());
        
        logger.info("--- ACT: Call with valid parameters ---");
        service.processAndSendDataWithPriority("Valid", 5);
        
        logger.info("--- ASSERT: Verify with complex matchers ---");
        verify(mockApi).sendDataWithPriority(
            argThat(data -> data != null && data.length() > 0),
            anyInt()
        );
        logger.info("✓ Test passed: Complex matcher combination worked");
    }
}
