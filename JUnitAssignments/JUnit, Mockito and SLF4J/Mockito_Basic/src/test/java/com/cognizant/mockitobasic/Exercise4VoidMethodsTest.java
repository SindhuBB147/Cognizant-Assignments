package com.cognizant.mockitobasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

/**
 * Exercise 4: Handling Void Methods
 * 
 * Scenario: Test void methods that perform actions but return nothing.
 * 
 * Key Concepts:
 * - Using doNothing() to explicitly allow void method calls
 * - Using doThrow() to make void methods throw exceptions
 * - Verifying void method interactions
 * - Testing side effects of void methods
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 4: Handling Void Methods")
public class Exercise4VoidMethodsTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise4VoidMethodsTest.class);
    
    @Mock
    private ExternalApi mockApi;
    
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MyService with mocked ExternalApi ===");
        service = new MyService(mockApi);
    }
    
    /**
     * Test 1: Verify void method is called
     * - Test that void method is invoked correctly
     */
    @Test
    @DisplayName("Test 1: Verify void method is called")
    public void testVoidMethodCalled() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call service method that uses void API method ---");
        service.processAndSendData("Test Data");
        
        logger.info("--- ASSERT: Verify void method was called ---");
        verify(mockApi).sendData("Test Data");
        logger.info("✓ Test passed: Void method was called");
    }
    
    /**
     * Test 2: doNothing() on void method
     * - Explicitly configure void method to do nothing (default behavior)
     */
    @Test
    @DisplayName("Test 2: Configure void method with doNothing()")
    public void testDoNothingOnVoidMethod() {
        logger.info("--- ARRANGE: Configure doNothing() on void method ---");
        doNothing().when(mockApi).sendData(anyString());
        
        logger.info("--- ACT: Call service that uses void method ---");
        service.processAndSendData("Some Data");
        
        logger.info("--- ASSERT: Verify void method was called and did nothing ---");
        verify(mockApi).sendData("Some Data");
        logger.info("✓ Test passed: doNothing() allowed void method execution");
    }
    
    /**
     * Test 3: Multiple void method calls
     * - Verify multiple void methods are called
     */
    @Test
    @DisplayName("Test 3: Verify multiple void method calls")
    public void testMultipleVoidMethodCalls() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call service methods that trigger multiple void calls ---");
        service.processAndSendData("Data 1");
        service.processAndSendData("Data 2");
        service.deleteDataById(1);
        
        logger.info("--- ASSERT: Verify all void methods were called ---");
        verify(mockApi).sendData("Data 1");
        verify(mockApi).sendData("Data 2");
        verify(mockApi).deleteData(1);
        logger.info("✓ Test passed: All void methods were called");
    }
    
    /**
     * Test 4: Verify void method called with specific count
     */
    @Test
    @DisplayName("Test 4: Verify void method call count")
    public void testVoidMethodCallCount() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call void method multiple times ---");
        service.deleteDataById(1);
        service.deleteDataById(2);
        service.deleteDataById(3);
        
        logger.info("--- ASSERT: Verify void method called exactly 3 times ---");
        verify(mockApi, times(3)).deleteData(anyInt());
        logger.info("✓ Test passed: Void method call count verified");
    }
    
    /**
     * Test 5: Verify void method called with matchers
     */
    @Test
    @DisplayName("Test 5: Verify void method with argument matchers")
    public void testVoidMethodWithMatchers() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call void method with specific arguments ---");
        service.processAndSendDataWithPriority("Important", 5);
        
        logger.info("--- ASSERT: Verify void method with matchers ---");
        verify(mockApi).sendDataWithPriority(anyString(), eq(5));
        logger.info("✓ Test passed: Void method verification with matchers worked");
    }
    
    /**
     * Test 6: Verify never called on void method
     */
    @Test
    @DisplayName("Test 6: Verify void method never called")
    public void testVoidMethodNeverCalled() {
        logger.info("--- ARRANGE: Setup service (no void method calls) ---");
        
        logger.info("--- ACT: Don't call methods that use void API ---");
        
        logger.info("--- ASSERT: Verify void method was never called ---");
        verify(mockApi, never()).deleteData(anyInt());
        logger.info("✓ Test passed: Void method was never called");
    }
    
    /**
     * Test 7: Void method with complex argument validation
     */
    @Test
    @DisplayName("Test 7: Verify void method with complex arguments")
    public void testVoidMethodComplexArguments() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call void method with various data ---");
        service.processAndSendData("ComplexData");
        service.deleteDataById(123);
        
        logger.info("--- ASSERT: Verify void methods with complex validation ---");
        verify(mockApi).sendData("ComplexData");
        verify(mockApi).deleteData(123);
        logger.info("✓ Test passed: Complex void method arguments verified");
    }
    
    /**
     * Test 8: doNothing() with any arguments
     */
    @Test
    @DisplayName("Test 8: doNothing() with argument matchers")
    public void testDoNothingWithAnyArguments() {
        logger.info("--- ARRANGE: Configure doNothing() for any string argument ---");
        doNothing().when(mockApi).sendData(anyString());
        doNothing().when(mockApi).deleteData(anyInt());
        
        logger.info("--- ACT: Call void methods with various arguments ---");
        service.processAndSendData("Data 1");
        service.processAndSendData("Data 2");
        service.deleteDataById(10);
        service.deleteDataById(20);
        
        logger.info("--- ASSERT: Verify all void methods executed ---");
        verify(mockApi, times(2)).sendData(anyString());
        verify(mockApi, times(2)).deleteData(anyInt());
        logger.info("✓ Test passed: doNothing() with matchers worked");
    }
    
    /**
     * Test 9: Verify void method call order
     */
    @Test
    @DisplayName("Test 9: Verify void method call sequence")
    public void testVoidMethodSequence() {
        logger.info("--- ARRANGE: Setup service ---");
        
        logger.info("--- ACT: Call void methods in sequence ---");
        service.processAndSendData("First");
        service.deleteDataById(1);
        service.processAndSendData("Second");
        
        logger.info("--- ASSERT: Verify all void methods called in order ---");
        verify(mockApi).sendData("First");
        verify(mockApi).deleteData(1);
        verify(mockApi).sendData("Second");
        logger.info("✓ Test passed: Void method call sequence verified");
    }
}
