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
 * Exercise 1: Mocking Databases and Repositories
 * 
 * Tests a service that depends on a database repository.
 * Demonstrates mocking of data access patterns.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 1: Mocking Databases and Repositories")
public class Exercise1RepositoryMockingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise1RepositoryMockingTest.class);
    
    @Mock
    private Repository mockRepository;
    
    private Service service;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize Service with mocked Repository ===");
        service = new Service(mockRepository);
    }
    
    /**
     * Test 1: Basic repository mocking
     */
    @Test
    @DisplayName("Test 1: Mock repository and stub getData()")
    public void testServiceWithMockRepository() {
        logger.info("--- ARRANGE: Stub repository.getData() ---");
        when(mockRepository.getData()).thenReturn("Mock Data");
        
        logger.info("--- ACT: Call service.processData() ---");
        String result = service.processData();
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Processed Mock Data", result);
        verify(mockRepository).getData();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 2: Repository method with ID
     */
    @Test
    @DisplayName("Test 2: Get data by ID from repository")
    public void testProcessDataById() {
        logger.info("--- ARRANGE: Stub repository methods ---");
        when(mockRepository.exists(1)).thenReturn(true);
        when(mockRepository.getDataById(1)).thenReturn("Data for ID 1");
        
        logger.info("--- ACT: Call service.processDataById(1) ---");
        String result = service.processDataById(1);
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Processed Data for ID 1", result);
        verify(mockRepository).exists(1);
        verify(mockRepository).getDataById(1);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 3: Repository save operation
     */
    @Test
    @DisplayName("Test 3: Save data via repository")
    public void testSaveProcessedData() {
        logger.info("--- ARRANGE: Stub repository.saveData() ---");
        when(mockRepository.saveData("Processed Data")).thenReturn(true);
        
        logger.info("--- ACT: Call service.saveProcessedData() ---");
        boolean result = service.saveProcessedData("Processed Data");
        
        logger.info("--- ASSERT: Verify save was successful ---");
        assertTrue(result);
        verify(mockRepository).saveData("Processed Data");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 4: Repository returns null
     */
    @Test
    @DisplayName("Test 4: Handle null data from repository")
    public void testProcessDataWhenNull() {
        logger.info("--- ARRANGE: Stub getData() to return null ---");
        when(mockRepository.getData()).thenReturn(null);
        
        logger.info("--- ACT: Call service.processData() ---");
        String result = service.processData();
        
        logger.info("--- ASSERT: Verify null handling ---");
        assertEquals("No data", result);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 5: Repository ID does not exist
     */
    @Test
    @DisplayName("Test 5: Handle non-existent ID")
    public void testProcessDataByIdNotFound() {
        logger.info("--- ARRANGE: Stub exists() to return false ---");
        when(mockRepository.exists(999)).thenReturn(false);
        
        logger.info("--- ACT: Call service.processDataById(999) ---");
        String result = service.processDataById(999);
        
        logger.info("--- ASSERT: Verify not found message ---");
        assertEquals("ID not found", result);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 6: Multiple repository operations
     */
    @Test
    @DisplayName("Test 6: Multiple repository method calls")
    public void testMultipleRepositoryOperations() {
        logger.info("--- ARRANGE: Setup multiple stubs ---");
        when(mockRepository.getData()).thenReturn("Data1");
        when(mockRepository.exists(1)).thenReturn(true);
        when(mockRepository.getDataById(1)).thenReturn("Data1");
        when(mockRepository.saveData(anyString())).thenReturn(true);
        
        logger.info("--- ACT: Call multiple service methods ---");
        service.processData();
        service.processDataById(1);
        boolean saved = service.saveProcessedData("Data1");
        
        logger.info("--- ASSERT: Verify all operations ---");
        assertTrue(saved);
        verify(mockRepository).getData();
        verify(mockRepository).getDataById(1);
        verify(mockRepository).saveData("Data1");
        logger.info("✓ Test passed");
    }
}
