package com.cognizant.mockitobasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

/**
 * Exercise 6: Verifying Interaction Order
 * 
 * Scenario: Test that methods are called in a specific order.
 * 
 * Key Concepts:
 * - Using InOrder for verifying call sequence
 * - Ensuring methods are called in expected order
 * - Testing workflow sequences
 * - Validating method call dependencies and ordering
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 6: Verifying Interaction Order")
public class Exercise6InteractionOrderTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise6InteractionOrderTest.class);
    
    @Mock
    private ExternalApi mockApi;
    
    private MyService service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize MyService with mocked ExternalApi ===");
        service = new MyService(mockApi);
    }
    
    /**
     * Test 1: Verify method call order
     * - Use InOrder to ensure methods are called in correct sequence
     */
    @Test
    @DisplayName("Test 1: Verify simple method call order")
    public void testSimpleCallOrder() {
        logger.info("--- ARRANGE: Setup mock API ---");
        when(mockApi.validateData("Data")).thenReturn(true);
        
        logger.info("--- ACT: Call service that validates then sends data ---");
        service.validateAndSendData("Data");
        
        logger.info("--- ASSERT: Verify call order with InOrder ---");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).validateData("Data");
        inOrder.verify(mockApi).sendData("Data");
        logger.info("✓ Test passed: Methods called in correct order");
    }
    
    /**
     * Test 2: Verify order of multiple calls
     * - Ensure multiple operations happen in sequence
     */
    @Test
    @DisplayName("Test 2: Verify multiple calls in sequence")
    public void testMultipleCallSequence() {
        logger.info("--- ARRANGE: Setup mock API ---");
        
        logger.info("--- ACT: Call service methods in specific order ---");
        service.processAndSendData("First");
        service.processAndSendData("Second");
        service.deleteDataById(1);
        
        logger.info("--- ASSERT: Verify exact call order ---");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).sendData("First");
        inOrder.verify(mockApi).sendData("Second");
        inOrder.verify(mockApi).deleteData(1);
        logger.info("✓ Test passed: Multiple calls verified in order");
    }
    
    /**
     * Test 3: Verify order with mixed method calls
     */
    @Test
    @DisplayName("Test 3: Verify mixed method call order")
    public void testMixedMethodOrder() {
        logger.info("--- ARRANGE: Setup mocks ---");
        when(mockApi.getDataById(1)).thenReturn("Data 1");
        
        logger.info("--- ACT: Call different methods in sequence ---");
        service.fetchDataById(1);
        service.processAndSendData("Processed");
        service.deleteDataById(1);
        
        logger.info("--- ASSERT: Verify mixed method order ---");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getDataById(1);
        inOrder.verify(mockApi).sendData("Processed");
        inOrder.verify(mockApi).deleteData(1);
        logger.info("✓ Test passed: Mixed method order verified");
    }
    
    /**
     * Test 4: Verify method called multiple times
     * - Verify total number of calls
     */
    @Test
    @DisplayName("Test 4: Verify method called multiple times")
    public void testRepeatedCallOrder() {
        logger.info("--- ARRANGE: Setup mock API ---");
        when(mockApi.getData()).thenReturn("Data");
        
        logger.info("--- ACT: Call same method multiple times ---");
        service.fetchData();
        service.fetchData();
        service.fetchData();
        
        logger.info("--- ASSERT: Verify method called 3 times ---");
        verify(mockApi, times(3)).getData();
        logger.info("✓ Test passed: Repeated calls verified");
    }
    
    /**
     * Test 5: Verify no other calls happened
     * - Ensure only expected methods were called, nothing more
     */
    @Test
    @DisplayName("Test 5: Verify exact sequence with no extra calls")
    public void testExactSequenceNoExtras() {
        logger.info("--- ARRANGE: Setup mock API ---");
        
        logger.info("--- ACT: Make specific method calls ---");
        service.processAndSendData("Data");
        
        logger.info("--- ASSERT: Verify exact sequence and no other calls ---");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).sendData("Data");
        inOrder.verifyNoMoreInteractions();
        logger.info("✓ Test passed: Exact sequence verified with no extra calls");
    }
    
    /**
     * Test 6: Verify order across multiple mock objects
     * - Use InOrder with multiple mocks
     */
    @Test
    @DisplayName("Test 6: Verify order with multiple mocks")
    public void testOrderMultipleMocks() {
        logger.info("--- ARRANGE: Setup mocks ---");
        when(mockApi.getData()).thenReturn("Data1");
        
        logger.info("--- ACT: Call methods ---");
        service.fetchData();
        
        logger.info("--- ASSERT: Verify order on primary mock ---");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        logger.info("✓ Test passed: Mock order verified");
    }
    
    /**
     * Test 7: Verify order with parameter-specific calls
     */
    @Test
    @DisplayName("Test 7: Verify order with specific parameters")
    public void testParameterSpecificOrder() {
        logger.info("--- ARRANGE: Setup mock ---");
        
        logger.info("--- ACT: Call methods with different parameters ---");
        service.processAndSendDataWithPriority("Urgent", 10);
        service.processAndSendDataWithPriority("Normal", 5);
        service.deleteDataById(1);
        
        logger.info("--- ASSERT: Verify order with parameter matching ---");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).sendDataWithPriority("Urgent", 10);
        inOrder.verify(mockApi).sendDataWithPriority("Normal", 5);
        inOrder.verify(mockApi).deleteData(1);
        logger.info("✓ Test passed: Parameter-specific order verified");
    }
    
    /**
     * Test 8: Verify complex workflow order
     * - Test realistic workflow with multiple operations
     */
    @Test
    @DisplayName("Test 8: Verify complex workflow order")
    public void testComplexWorkflowOrder() {
        logger.info("--- ARRANGE: Setup mocks ---");
        when(mockApi.validateData("Important")).thenReturn(true);
        when(mockApi.getData()).thenReturn("Data");
        
        logger.info("--- ACT: Execute complex workflow ---");
        // Step 1: Fetch existing data
        service.fetchData();
        
        // Step 2: Validate new data
        service.validateAndSendData("Important");
        
        // Step 3: Delete old data
        service.deleteDataById(1);
        
        logger.info("--- ASSERT: Verify complete workflow order ---");
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).validateData("Important");
        inOrder.verify(mockApi).sendData("Important");
        inOrder.verify(mockApi).deleteData(1);
        logger.info("✓ Test passed: Complex workflow order verified");
    }
    
    /**
     * Test 9: Verify order doesn't break with multiple verification styles
     */
    @Test
    @DisplayName("Test 9: Combine InOrder with regular verify")
    public void testMixedVerificationStyles() {
        logger.info("--- ARRANGE: Setup mocks ---");
        when(mockApi.getDataById(1)).thenReturn("Data1");
        
        logger.info("--- ACT: Call methods ---");
        service.fetchDataById(1);
        service.processAndSendData("Data1");
        
        logger.info("--- ASSERT: Use both InOrder and regular verify ---");
        // Regular verification (any order)
        verify(mockApi).getDataById(1);
        
        // InOrder verification (specific order)
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getDataById(1);
        inOrder.verify(mockApi).sendData("Data1");
        
        logger.info("✓ Test passed: Mixed verification styles worked");
    }
}
