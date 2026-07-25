package com.cognizant.mockitobasic;

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
import static org.mockito.ArgumentMatchers.*;

/**
 * Exercise 7: Handling Void Methods with Exceptions
 * 
 * Scenario: Test void methods that throw exceptions.
 * 
 * Key Concepts:
 * - Using doThrow() to make void methods throw exceptions
 * - Testing exception handling in services
 * - Verifying error conditions
 * - Combining exception throwing with argument matchers
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 7: Handling Void Methods with Exceptions")
public class Exercise7VoidMethodsExceptionsTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise7VoidMethodsExceptionsTest.class);
    
    @Mock
    private ExternalApi mockApi;
    
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MyService with mocked ExternalApi ===");
        service = new MyService(mockApi);
    }
    
    /**
     * Test 1: Void method throws exception
     * - Configure mock to throw exception
     * - Verify exception is propagated
     */
    @Test
    @DisplayName("Test 1: Void method throws exception")
    public void testVoidMethodThrowsException() {
        logger.info("--- ARRANGE: Configure void method to throw exception ---");
        doThrow(new RuntimeException("API Error"))
            .when(mockApi).sendData(anyString());
        
        logger.info("--- ACT: Call service method that uses void API ---");
        logger.info("--- ASSERT: Verify exception is thrown ---");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("Test Data");
        });
        
        assertEquals("API Error", exception.getMessage());
        logger.info("✓ Test passed: Exception was thrown as expected");
    }
    
    /**
     * Test 2: Throw specific exception type
     */
    @Test
    @DisplayName("Test 2: Throw specific exception type")
    public void testThrowSpecificException() {
        logger.info("--- ARRANGE: Configure void method to throw IOException ---");
        doThrow(new IllegalArgumentException("Invalid data"))
            .when(mockApi).sendData(anyString());
        
        logger.info("--- ACT & ASSERT: Verify correct exception type ---");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.processAndSendData("Invalid");
        });
        
        assertEquals("Invalid data", exception.getMessage());
        logger.info("✓ Test passed: Correct exception type thrown");
    }
    
    /**
     * Test 3: Throw exception with specific argument
     * - Only throw exception for specific parameter value
     */
    @Test
    @DisplayName("Test 3: Throw exception on multiple calls, then succeed")
    public void testThrowForSpecificArgument() {
        logger.info("--- ARRANGE: First call throws, second succeeds ---");
        doThrow(new RuntimeException("Cannot send"))
            .doNothing()
            .when(mockApi).sendData(anyString());
        
        logger.info("--- ACT: First call should throw ---");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("Data");
        });
        assertEquals("Cannot send", exception.getMessage());
        
        logger.info("--- ACT: Second call should succeed ---");
        service.processAndSendData("Data");
        logger.info("✓ Test passed: Sequential throw/succeed behavior");
    }
    
    /**
     * Test 4: Multiple throws in sequence
     * - First call throws, second call succeeds
     */
    @Test
    @DisplayName("Test 4: Void method throws on first call, succeeds on second")
    public void testThrowThenSucceed() {
        logger.info("--- ARRANGE: Configure to throw first time, then succeed ---");
        doThrow(new RuntimeException("Temporary Error"))
            .doNothing()
            .when(mockApi).sendData(anyString());
        
        logger.info("--- ACT: First call should throw ---");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("Data");
        });
        assertEquals("Temporary Error", exception.getMessage());
        
        logger.info("--- ACT: Second call should succeed ---");
        service.processAndSendData("Data");
        
        logger.info("--- ASSERT: Verify both calls happened ---");
        verify(mockApi, times(2)).sendData("Data");
        logger.info("✓ Test passed: First threw, second succeeded");
    }
    
    /**
     * Test 5: Different exceptions for different methods
     */
    @Test
    @DisplayName("Test 5: Different methods throw different exceptions")
    public void testDifferentMethodsThrowDifferentExceptions() {
        logger.info("--- ARRANGE: Configure different exceptions ---");
        doThrow(new RuntimeException("Send Error"))
            .when(mockApi).sendData(anyString());
        doThrow(new IllegalStateException("Delete Error"))
            .when(mockApi).deleteData(anyInt());
        
        logger.info("--- ACT & ASSERT: Verify first exception ---");
        RuntimeException sendError = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("Data");
        });
        assertEquals("Send Error", sendError.getMessage());
        
        logger.info("--- ACT & ASSERT: Verify second exception ---");
        IllegalStateException deleteError = assertThrows(IllegalStateException.class, () -> {
            service.deleteDataById(1);
        });
        assertEquals("Delete Error", deleteError.getMessage());
        logger.info("✓ Test passed: Different exceptions thrown correctly");
    }
    
    /**
     * Test 6: Throw exception on specific conditions
     */
    @Test
    @DisplayName("Test 6: Throw exception in specific scenario")
    public void testThrowWithArgumentMatcher() {
        logger.info("--- ARRANGE: Throw on first call ---");
        doThrow(new IllegalArgumentException("Delete error"))
            .doNothing()
            .when(mockApi).deleteData(anyInt());
        
        logger.info("--- ACT & ASSERT: First call should throw ---");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteDataById(100);
        });
        assertEquals("Delete error", exception.getMessage());
        
        logger.info("--- ACT: Second call should succeed ---");
        service.deleteDataById(50);
        logger.info("✓ Test passed: Exception and success sequence");
    }
    
    /**
     * Test 7: Exception with custom message
     */
    @Test
    @DisplayName("Test 7: Throw exception with descriptive message")
    public void testExceptionWithCustomMessage() {
        logger.info("--- ARRANGE: Create exception with detailed message ---");
        String errorMessage = "Failed to send data to external API: Connection timeout after 30 seconds";
        doThrow(new RuntimeException(errorMessage))
            .when(mockApi).sendData(anyString());
        
        logger.info("--- ACT & ASSERT: Verify detailed error message ---");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("Data");
        });
        assertTrue(exception.getMessage().contains("timeout"));
        logger.info("✓ Test passed: Custom error message preserved");
    }
    
    /**
     * Test 8: Throw exception with cause
     */
    @Test
    @DisplayName("Test 8: Throw exception with cause chain")
    public void testThrowWithCause() {
        logger.info("--- ARRANGE: Create exception with cause ---");
        Throwable cause = new Exception("Network unreachable");
        doThrow(new RuntimeException("API call failed", cause))
            .when(mockApi).sendData(anyString());
        
        logger.info("--- ACT & ASSERT: Verify exception cause ---");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("Data");
        });
        assertEquals("API call failed", exception.getMessage());
        assertEquals("Network unreachable", exception.getCause().getMessage());
        logger.info("✓ Test passed: Exception cause chain preserved");
    }
    
    /**
     * Test 9: Combination of throws and multiple scenarios
     */
    @Test
    @DisplayName("Test 9: Complex scenario with send exception")
    public void testComplexVoidMethodScenario() {
        logger.info("--- ARRANGE: Setup different behaviors ---");
        doThrow(new RuntimeException("Send error"))
            .doThrow(new RuntimeException("Retry send error"))
            .doNothing()
            .when(mockApi).sendData(anyString());
        
        logger.info("--- ACT & ASSERT: First call throws ---");
        RuntimeException sendError1 = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("data1");
        });
        assertEquals("Send error", sendError1.getMessage());
        
        logger.info("--- ACT & ASSERT: Second call throws different error ---");
        RuntimeException sendError2 = assertThrows(RuntimeException.class, () -> {
            service.processAndSendData("data2");
        });
        assertEquals("Retry send error", sendError2.getMessage());
        
        logger.info("--- ACT & ASSERT: Third call succeeds ---");
        service.processAndSendData("data3");
        verify(mockApi, times(3)).sendData(anyString());
        logger.info("✓ Test passed: Complex exception sequence handled");
    }
}
