package com.cognizant.junitadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 2: Writing Basic JUnit Tests
 * 
 * This test class demonstrates how to write basic tests for a Java class.
 * It tests the Calculator class methods using simple assertions.
 * 
 * Key Concepts:
 * - Test methods must be annotated with @Test
 * - Test methods should have descriptive names or use @DisplayName
 * - Each test should focus on a single behavior
 * - Use @BeforeEach to set up common test fixtures
 */
@DisplayName("Exercise 2: Writing Basic JUnit Tests")
public class Exercise2BasicTestsTest {
    
    private Calculator calculator;
    
    /**
     * Setup method: Initializes test fixtures before each test.
     * This method runs before every test method.
     * 
     * In JUnit 4, this would be @Before
     */
    @BeforeEach
    void setUp() {
        // Create a new Calculator instance for each test
        calculator = new Calculator();
    }
    
    /**
     * Test the add method with positive numbers.
     */
    @Test
    @DisplayName("Should add two positive numbers correctly")
    void testAddPositiveNumbers() {
        // Arrange: Set up test data
        int a = 5;
        int b = 3;
        
        // Act: Call the method to be tested
        int result = calculator.add(a, b);
        
        // Assert: Verify the result
        assertEquals(8, result, "5 + 3 should equal 8");
    }
    
    /**
     * Test the add method with negative numbers.
     */
    @Test
    @DisplayName("Should add negative numbers correctly")
    void testAddNegativeNumbers() {
        // Arrange
        int a = -5;
        int b = -3;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        assertEquals(-8, result, "-5 + -3 should equal -8");
    }
    
    /**
     * Test the subtract method.
     */
    @Test
    @DisplayName("Should subtract numbers correctly")
    void testSubtract() {
        // Arrange
        int a = 10;
        int b = 4;
        
        // Act
        int result = calculator.subtract(a, b);
        
        // Assert
        assertEquals(6, result, "10 - 4 should equal 6");
    }
    
    /**
     * Test the multiply method.
     */
    @Test
    @DisplayName("Should multiply numbers correctly")
    void testMultiply() {
        // Arrange
        int a = 6;
        int b = 7;
        
        // Act
        int result = calculator.multiply(a, b);
        
        // Assert
        assertEquals(42, result, "6 * 7 should equal 42");
    }
    
    /**
     * Test the divide method with valid divisor.
     */
    @Test
    @DisplayName("Should divide numbers correctly")
    void testDivide() {
        // Arrange
        int a = 20;
        int b = 4;
        
        // Act
        int result = calculator.divide(a, b);
        
        // Assert
        assertEquals(5, result, "20 / 4 should equal 5");
    }
    
    /**
     * Test the isPositive method.
     */
    @Test
    @DisplayName("Should identify positive numbers")
    void testIsPositive() {
        // Arrange & Act
        boolean result = calculator.isPositive(5);
        
        // Assert
        assertTrue(result, "5 should be identified as positive");
    }
}
