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
 * Exercise 4: Mocking Network Interactions
 * 
 * Tests a service that interacts with network resources.
 * Demonstrates mocking of network communication patterns.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 4: Mocking Network Interactions")
public class Exercise4NetworkMockingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise4NetworkMockingTest.class);
    
    @Mock
    private NetworkClient mockNetworkClient;
    
    private NetworkService networkService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize NetworkService with mocked NetworkClient ===");
        networkService = new NetworkService(mockNetworkClient);
    }
    
    /**
     * Test 1: Basic network connection
     */
    @Test
    @DisplayName("Test 1: Mock network client and connect")
    public void testServiceWithMockNetworkClient() {
        logger.info("--- ARRANGE: Stub network client ---");
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");
        
        logger.info("--- ACT: Call networkService.connectToServer() ---");
        String result = networkService.connectToServer();
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Connected to Mock Connection", result);
        verify(mockNetworkClient).connect();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 2: Connect to specific address
     */
    @Test
    @DisplayName("Test 2: Connect to specific server address")
    public void testConnectToAddress() {
        logger.info("--- ARRANGE: Stub connectToAddress ---");
        when(mockNetworkClient.connectToAddress("192.168.1.1")).thenReturn("Connected");
        
        logger.info("--- ACT: Call networkService.connectToAddress() ---");
        String result = networkService.connectToAddress("192.168.1.1");
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Connected to Connected", result);
        verify(mockNetworkClient).connectToAddress("192.168.1.1");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 3: Send network data
     */
    @Test
    @DisplayName("Test 3: Send data over network")
    public void testSendNetworkData() {
        logger.info("--- ARRANGE: Stub sendData ---");
        when(mockNetworkClient.sendData("Hello")).thenReturn("Server ACK");
        
        logger.info("--- ACT: Call networkService.sendNetworkData() ---");
        String result = networkService.sendNetworkData("Hello");
        
        logger.info("--- ASSERT: Verify result ---");
        assertTrue(result.contains("Hello"));
        assertTrue(result.contains("Server ACK"));
        verify(mockNetworkClient).sendData("Hello");
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 4: Receive network data
     */
    @Test
    @DisplayName("Test 4: Receive data from network")
    public void testReceiveNetworkData() {
        logger.info("--- ARRANGE: Stub receiveData ---");
        when(mockNetworkClient.receiveData()).thenReturn("Server Response");
        
        logger.info("--- ACT: Call networkService.receiveNetworkData() ---");
        String result = networkService.receiveNetworkData();
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals("Received: Server Response", result);
        verify(mockNetworkClient).receiveData();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 5: Disconnect from server
     */
    @Test
    @DisplayName("Test 5: Disconnect from server")
    public void testDisconnect() {
        logger.info("--- ARRANGE: Stub disconnect ---");
        when(mockNetworkClient.disconnect()).thenReturn("Disconnected Successfully");
        
        logger.info("--- ACT: Call networkService.disconnectFromServer() ---");
        String result = networkService.disconnectFromServer();
        
        logger.info("--- ASSERT: Verify result ---");
        assertTrue(result.contains("Disconnected"));
        verify(mockNetworkClient).disconnect();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 6: Full network lifecycle
     */
    @Test
    @DisplayName("Test 6: Complete network connection lifecycle")
    public void testNetworkLifecycle() {
        logger.info("--- ARRANGE: Setup network stubs ---");
        when(mockNetworkClient.connect()).thenReturn("Connection OK");
        when(mockNetworkClient.sendData(anyString())).thenReturn("Data Sent");
        when(mockNetworkClient.receiveData()).thenReturn("Response");
        when(mockNetworkClient.disconnect()).thenReturn("Closed");
        
        logger.info("--- ACT: Execute full network sequence ---");
        networkService.connectToServer();
        networkService.sendNetworkData("Test");
        networkService.receiveNetworkData();
        networkService.disconnectFromServer();
        
        logger.info("--- ASSERT: Verify all operations ---");
        verify(mockNetworkClient).connect();
        verify(mockNetworkClient).sendData("Test");
        verify(mockNetworkClient).receiveData();
        verify(mockNetworkClient).disconnect();
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 7: Network error handling
     */
    @Test
    @DisplayName("Test 7: Handle network connection failure")
    public void testNetworkConnectionFailure() {
        logger.info("--- ARRANGE: Stub failed connection ---");
        when(mockNetworkClient.connect()).thenReturn("Connection Failed");
        
        logger.info("--- ACT: Call networkService.connectToServer() ---");
        String result = networkService.connectToServer();
        
        logger.info("--- ASSERT: Verify error message ---");
        assertTrue(result.contains("Connection Failed"));
        logger.info("✓ Test passed");
    }
    
    /**
     * Test 8: Multiple network send/receive
     */
    @Test
    @DisplayName("Test 8: Multiple network send/receive operations")
    public void testMultipleNetworkOperations() {
        logger.info("--- ARRANGE: Setup multiple stubs ---");
        when(mockNetworkClient.sendData(anyString())).thenReturn("OK");
        when(mockNetworkClient.receiveData())
            .thenReturn("Response1")
            .thenReturn("Response2")
            .thenReturn("Response3");
        
        logger.info("--- ACT: Call multiple network operations ---");
        networkService.sendNetworkData("Data1");
        networkService.receiveNetworkData();
        networkService.receiveNetworkData();
        networkService.receiveNetworkData();
        
        logger.info("--- ASSERT: Verify all calls ---");
        verify(mockNetworkClient).sendData("Data1");
        verify(mockNetworkClient, times(3)).receiveData();
        logger.info("✓ Test passed");
    }
}
