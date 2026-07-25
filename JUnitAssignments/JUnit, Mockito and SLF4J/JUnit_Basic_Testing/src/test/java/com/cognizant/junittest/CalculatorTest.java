package com.cognizant.junittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sample test class for Calculator.
 * Demonstrates basic JUnit 5 testing practices.
 */
@DisplayName("Calculator Tests")
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        // Arrange: Initialize calculator before each test
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Should add two positive numbers correctly")
    void testAddPositiveNumbers() {
        // Act: Call the method to be tested
        int result = calculator.add(5, 3);
        
        // Assert: Verify the result
        assertEquals(8, result, "5 + 3 should equal 8");
    }
    
    @Test
    @DisplayName("Should subtract two positive numbers correctly")
    void testSubtractPositiveNumbers() {
        // Act
        int result = calculator.subtract(10, 4);
        
        // Assert
        assertEquals(6, result, "10 - 4 should equal 6");
    }
}
