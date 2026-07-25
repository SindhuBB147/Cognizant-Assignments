package com.cognizant.mockitoadvanced;

/**
 * NetworkClient interface for network interactions.
 * Demonstrates mocking of network communication.
 */
public interface NetworkClient {
    
    /**
     * Establishes connection to a server.
     * 
     * @return connection status string
     */
    String connect();
    
    /**
     * Connects to a server with specific address.
     * 
     * @param address the server address
     * @return connection status
     */
    String connectToAddress(String address);
    
    /**
     * Sends data over the network.
     * 
     * @param data the data to send
     * @return response from server
     */
    String sendData(String data);
    
    /**
     * Receives data from the network.
     * 
     * @return received data
     */
    String receiveData();
    
    /**
     * Disconnects from the server.
     * 
     * @return disconnection status
     */
    String disconnect();
}
