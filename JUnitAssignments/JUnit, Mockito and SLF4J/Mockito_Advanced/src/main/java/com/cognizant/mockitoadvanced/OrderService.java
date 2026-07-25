package com.cognizant.mockitoadvanced;

/**
 * Order service interface for advanced mocking scenarios.
 */
public interface OrderService {
    
    /**
     * Places an order.
     * 
     * @param orderId the order ID
     * @param amount the order amount
     * @return true if order placed successfully
     */
    boolean placeOrder(String orderId, double amount);
    
    /**
     * Cancels an order.
     * 
     * @param orderId the order ID
     * @return true if order cancelled successfully
     */
    boolean cancelOrder(String orderId);
    
    /**
     * Gets the order status.
     * 
     * @param orderId the order ID
     * @return the status of the order
     */
    String getOrderStatus(String orderId);
}
