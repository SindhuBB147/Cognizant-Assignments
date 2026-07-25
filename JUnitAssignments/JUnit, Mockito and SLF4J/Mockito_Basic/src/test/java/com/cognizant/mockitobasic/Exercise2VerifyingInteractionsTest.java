package com.cognizant.mockitobasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

/**
 * Exercise 2: Verifying Interactions
 * 
 * Scenario: Test that specific methods are called with specific arguments.
 * 
 * Key Concepts:
 * - Using verify() to assert method calls
 * - Checking method call counts with times()
 * - Ensuring methods are never called with never()
 * - Verifying interaction sequence
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 2: Verifying Interactions")
public class Exercise2VerifyingInteractionsTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise2VerifyingInteractionsTest.class);
    
    @Mock
    private ExternalApi mockApi;
    
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MyService with mocked ExternalApi ===");
        service = new MyService(mockApi);
    }
    
    /**
     * Test 1: Verify method is called
     * - Call a service method
     * - Verify that the mocked API method was called
     */
    @Test
    @DisplayName("Test 1: Verify method was called")
    public void testVerifyMethodCalled() {
        logger.info("--- ARRANGE: Stub mockApi.getData() ---");
        when(mockApi.getData()).thenReturn("Test Data");
        
        logger.info("--- ACT: Call service.fetchData() ---");
        service.fetchData();
        
        logger.info("--- ASSERT: Verify mockApi.getData() was called ---");
        verify(mockApi).getData();
        logger.info("✓ Test passed: Method was called");
    }
    
    /**
     * Test 2: Verify method called with specific arguments
     * - Call service with specific data
     * - Verify API was called with that exact data
     */
    @Test
    @DisplayName("Test 2: Verify method called with specific arguments")
    public void testVerifyMethodCalledWithArgs() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call service.processAndSendData() with specific data ---");
        String testData = "Important Data";
        service.processAndSendData(testData);
        
        logger.info("--- ASSERT: Verify mockApi.sendData() was called with exact argument ---");
        verify(mockApi).sendData(testData);
        logger.info("✓ Test passed: Method called with correct argument");
    }
    
    /**
     * Test 3: Verify method called with specific numeric argument
     */
    @Test
    @DisplayName("Test 3: Verify method called with specific numeric argument")
    public void testVerifyNumericArgument() {
        logger.info("--- ARRANGE: Stub mockApi.getDataById() ---");
        when(mockApi.getDataById(42)).thenReturn("Answer to everything");
        
        logger.info("--- ACT: Call service.fetchDataById(42) ---");
        service.fetchDataById(42);
        
        logger.info("--- ASSERT: Verify mockApi.getDataById(42) was called ---");
        verify(mockApi).getDataById(42);
        logger.info("✓ Test passed: Method called with correct numeric argument");
    }
    
    /**
     * Test 4: Verify method called exactly N times
     * - Call service method multiple times
     * - Verify the API method was called exactly that many times
     */
    @Test
    @DisplayName("Test 4: Verify method called exactly N times")
    public void testVerifyCallCount() {
        logger.info("--- ARRANGE: Stub mockApi.getData() ---");
        when(mockApi.getData()).thenReturn("Data");
        
        logger.info("--- ACT: Call service.fetchData() three times ---");
        service.fetchData();
        service.fetchData();
        service.fetchData();
        
        logger.info("--- ASSERT: Verify mockApi.getData() was called exactly 3 times ---");
        verify(mockApi, times(3)).getData();
        logger.info("✓ Test passed: Method called exactly 3 times");
    }
    
    /**
     * Test 5: Verify method called once (default)
     */
    @Test
    @DisplayName("Test 5: Verify method called exactly once")
    public void testVerifyCalledOnce() {
        logger.info("--- ARRANGE: Stub mockApi.getData() ---");
        when(mockApi.getData()).thenReturn("Single Data");
        
        logger.info("--- ACT: Call service.fetchData() once ---");
        service.fetchData();
        
        logger.info("--- ASSERT: Verify mockApi.getData() was called once ---");
        verify(mockApi, times(1)).getData();
        logger.info("✓ Test passed: Method called exactly once");
    }
    
    /**
     * Test 6: Verify method was never called
     * - Test that specific methods are never invoked
     */
    @Test
    @DisplayName("Test 6: Verify method was never called")
    public void testVerifyNeverCalled() {
        logger.info("--- ARRANGE: Setup service (no method calls) ---");
        
        logger.info("--- ACT: Don't call any service methods that would use API ---");
        
        logger.info("--- ASSERT: Verify mockApi.getData() was never called ---");
        verify(mockApi, never()).getData();
        logger.info("✓ Test passed: Method was never called");
    }
    
    /**
     * Test 7: Verify void method interaction
     * - Verify that void methods are called correctly
     */
    @Test
    @DisplayName("Test 7: Verify void method was called")
    public void testVerifyVoidMethod() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call service.processAndSendData() ---");
        service.processAndSendData("Test Data");
        
        logger.info("--- ASSERT: Verify mockApi.sendData() void method was called ---");
        verify(mockApi).sendData("Test Data");
        logger.info("✓ Test passed: Void method was called");
    }
    
    /**
     * Test 8: Verify multiple different method calls in sequence
     * - Verify that multiple methods are called in correct sequence
     */
    @Test
    @DisplayName("Test 8: Verify multiple method calls")
    public void testVerifyMultipleCalls() {
        logger.info("--- ARRANGE: Stub both methods ---");
        when(mockApi.getData()).thenReturn("Data");
        when(mockApi.getDataById(1)).thenReturn("Data 1");
        
        logger.info("--- ACT: Call both service methods ---");
        service.fetchData();
        service.fetchDataById(1);
        
        logger.info("--- ASSERT: Verify both API methods were called ---");
        verify(mockApi).getData();
        verify(mockApi).getDataById(1);
        logger.info("✓ Test passed: Both methods were called");
    }
    
    /**
     * Test 9: Verify with priority parameters
     */
    @Test
    @DisplayName("Test 9: Verify method called with multiple parameters")
    public void testVerifyMultipleParameters() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call service with data and priority ---");
        service.processAndSendDataWithPriority("Important", 5);
        
        logger.info("--- ASSERT: Verify API method called with both parameters ---");
        verify(mockApi).sendDataWithPriority("Important", 5);
        logger.info("✓ Test passed: Method called with correct parameters");
    }
}
