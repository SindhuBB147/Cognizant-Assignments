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
 * Exercise 5: Mocking Multiple Return Values
 * 
 * Tests a service that calls a method multiple times with different return values.
 * Demonstrates chaining return values and handling sequential calls.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 5: Mocking Multiple Return Values")
public class Exercise5MultipleReturnsTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise5MultipleReturnsTest.class);
    
    @Mock
    private Repository mockRepository;
    
    private Service service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize Service with mocked Repository ===");
        service = new Service(mockRepository);
    }
    
    /**
     * Test 1: Basic multiple return values
     */
    @Test
    @DisplayName("Test 1: Mock returns different values on consecutive calls")
    public void testServiceWithMultipleReturnValues() {
        logger.info("--- ARRANGE: Setup multiple return values ---");
        when(mockRepository.getData())
            .thenReturn("First Mock Data")
            .thenReturn("Second Mock Data");
        
        logger.info("--- ACT: Call processData multiple times ---");
        String firstResult = service.processData();
        String secondResult = service.processData();
        
        logger.info("--- ASSERT: Verify different results ---");
        assertEquals("Processed First Mock Data", firstResult);
        assertEquals("Processed Second Mock Data", secondResult);
        verify(mockRepository, times(2)).getData();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 2: Three sequential return values
     */
    @Test
    @DisplayName("Test 2: Three consecutive return values")
    public void testThreeSequentialReturns() {
        logger.info("--- ARRANGE: Setup three return values ---");
        when(mockRepository.getData())
            .thenReturn("Data One")
            .thenReturn("Data Two")
            .thenReturn("Data Three");
        
        logger.info("--- ACT: Call method three times ---");
        String first = service.processData();
        String second = service.processData();
        String third = service.processData();
        
        logger.info("--- ASSERT: Verify all returns ---");
        assertEquals("Processed Data One", first);
        assertEquals("Processed Data Two", second);
        assertEquals("Processed Data Three", third);
        verify(mockRepository, times(3)).getData();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 3: Last return value repeats
     */
    @Test
    @DisplayName("Test 3: Last return value repeats on subsequent calls")
    public void testLastReturnRepeats() {
        logger.info("--- ARRANGE: Setup return values, last will repeat ---");
        when(mockRepository.getData())
            .thenReturn("First")
            .thenReturn("Last");
        
        logger.info("--- ACT: Call four times ---");
        String first = service.processData();
        String second = service.processData();
        String third = service.processData();
        String fourth = service.processData();
        
        logger.info("--- ASSERT: Verify last value repeats ---");
        assertEquals("Processed First", first);
        assertEquals("Processed Last", second);
        assertEquals("Processed Last", third);
        assertEquals("Processed Last", fourth);
        verify(mockRepository, times(4)).getData();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 4: Multiple returns with getDataById
     */
    @Test
    @DisplayName("Test 4: Multiple return values for ID-based queries")
    public void testMultipleReturnsWithId() {
        logger.info("--- ARRANGE: Setup returns for specific ID ---");
        when(mockRepository.exists(1)).thenReturn(true);
        when(mockRepository.getDataById(1))
            .thenReturn("User One")
            .thenReturn("User One Updated")
            .thenReturn("User One Modified");
        
        logger.info("--- ACT: Call multiple times with same ID ---");
        String first = service.processDataById(1);
        String second = service.processDataById(1);
        String third = service.processDataById(1);
        
        logger.info("--- ASSERT: Verify progression ---");
        assertEquals("Processed User One", first);
        assertEquals("Processed User One Updated", second);
        assertEquals("Processed User One Modified", third);
        verify(mockRepository, times(3)).getDataById(1);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 5: Alternating return values
     */
    @Test
    @DisplayName("Test 5: Alternating between two return values")
    public void testAlternatingReturnValues() {
        logger.info("--- ARRANGE: Setup alternating returns ---");
        when(mockRepository.getData())
            .thenReturn("Even")
            .thenReturn("Odd")
            .thenReturn("Even")
            .thenReturn("Odd");
        
        logger.info("--- ACT: Call four times ---");
        String[] results = new String[4];
        for (int i = 0; i < 4; i++) {
            results[i] = service.processData();
        }
        
        logger.info("--- ASSERT: Verify alternation ---");
        assertEquals("Processed Even", results[0]);
        assertEquals("Processed Odd", results[1]);
        assertEquals("Processed Even", results[2]);
        assertEquals("Processed Odd", results[3]);
        verify(mockRepository, times(4)).getData();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 6: Different IDs with multiple returns each
     */
    @Test
    @DisplayName("Test 6: Different IDs with separate return chains")
    public void testMultipleDifferentIds() {
        logger.info("--- ARRANGE: Setup returns for multiple IDs ---");
        when(mockRepository.exists(1)).thenReturn(true);
        when(mockRepository.exists(2)).thenReturn(true);
        when(mockRepository.getDataById(1))
            .thenReturn("User1-First")
            .thenReturn("User1-Second");
        when(mockRepository.getDataById(2))
            .thenReturn("User2-First")
            .thenReturn("User2-Second");
        
        logger.info("--- ACT: Call multiple times with different IDs ---");
        String u1_1 = service.processDataById(1);
        String u2_1 = service.processDataById(2);
        String u1_2 = service.processDataById(1);
        String u2_2 = service.processDataById(2);
        
        logger.info("--- ASSERT: Verify separate chains ---");
        assertEquals("Processed User1-First", u1_1);
        assertEquals("Processed User2-First", u2_1);
        assertEquals("Processed User1-Second", u1_2);
        assertEquals("Processed User2-Second", u2_2);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 7: Multiple operations combined
     */
    @Test
    @DisplayName("Test 7: Combined operations with multiple returns")
    public void testMultipleOperationsCombined() {
        logger.info("--- ARRANGE: Setup returns for multiple methods ---");
        when(mockRepository.getData())
            .thenReturn("Data1")
            .thenReturn("Data2");
        when(mockRepository.getDataById(1))
            .thenReturn("ById1")
            .thenReturn("ById2");
        when(mockRepository.exists(1)).thenReturn(true);
        
        logger.info("--- ACT: Call different methods multiple times ---");
        String d1 = service.processData();
        String b1 = service.processDataById(1);
        String d2 = service.processData();
        String b2 = service.processDataById(1);
        
        logger.info("--- ASSERT: Verify all operations ---");
        assertEquals("Processed Data1", d1);
        assertEquals("Processed ById1", b1);
        assertEquals("Processed Data2", d2);
        assertEquals("Processed ById2", b2);
        verify(mockRepository, times(2)).getData();
        verify(mockRepository, times(2)).getDataById(1);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 8: Using thenReturn() followed by thenThrow()
     */
    @Test
    @DisplayName("Test 8: Return value followed by exception")
    public void testReturnFollowedByException() {
        logger.info("--- ARRANGE: Setup return then exception ---");
        when(mockRepository.getData())
            .thenReturn("Success")
            .thenThrow(new RuntimeException("Service Error"));
        
        logger.info("--- ACT: First call succeeds, second throws ---");
        String result = service.processData();
        
        logger.info("--- ASSERT: Verify success ---");
        assertEquals("Processed Success", result);
        
        logger.info("--- ASSERT: Verify exception on second call ---");
        assertThrows(RuntimeException.class, () -> service.processData());
        logger.info("✓ Test passed");
    }
}
