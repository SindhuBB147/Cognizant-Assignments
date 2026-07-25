package com.cognizant.mockitoadvanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for Exercise 4: Mocking Network Interactions.
 * Demonstrates testing network communication with mocked client.
 */
public class NetworkService {
    
    private static final Logger logger = LoggerFactory.getLogger(NetworkService.class);
    private final NetworkClient networkClient;
    
    /**
     * Constructor with network client dependency injection.
     */
    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }
    
    /**
     * Connects to server and returns status.
     */
    public String connectToServer() {
        logger.info("Connecting to server");
        String connection = networkClient.connect();
        String result = "Connected to " + connection;
        logger.info(result);
        return result;
    }
    
    /**
     * Connects to specific server address.
     */
    public String connectToAddress(String address) {
        logger.info("Connecting to address: {}", address);
        String connection = networkClient.connectToAddress(address);
        return "Connected to " + connection;
    }
    
    /**
     * Sends data over network.
     */
    public String sendNetworkData(String data) {
        logger.info("Sending network data: {}", data);
        String response = networkClient.sendData(data);
        return "Sent " + data + " - Response: " + response;
    }
    
    /**
     * Receives data from network.
     */
    public String receiveNetworkData() {
        logger.info("Receiving network data");
        String data = networkClient.receiveData();
        return "Received: " + data;
    }
    
    /**
     * Disconnects from server.
     */
    public String disconnectFromServer() {
        logger.info("Disconnecting from server");
        String status = networkClient.disconnect();
        return "Disconnected - Status: " + status;
    }
}
