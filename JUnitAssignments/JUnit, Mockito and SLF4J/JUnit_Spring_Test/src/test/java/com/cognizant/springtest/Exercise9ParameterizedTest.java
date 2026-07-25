package com.cognizant.springtest;

import com.cognizant.springtest.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 9: Parameterized Test with JUnit
 * 
 * Uses @ParameterizedTest to test multiple inputs
 */
@SpringBootTest
@DisplayName("Exercise 9: Parameterized Test with JUnit")
public class Exercise9ParameterizedTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise9ParameterizedTest.class);
    
    @Autowired
    private CalculatorService calculatorService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize CalculatorService ===");
    }
    
    /**
     * Parameterized test with CSV source for calculator addition.
     */
    @ParameterizedTest
    @CsvSource({
        "1,2,3",
        "5,3,8",
        "0,0,0",
        "-5,-3,-8",
        "10,-3,7",
        "100,200,300"
    })
    @DisplayName("Test 1: Add with multiple inputs (CSV)")
    public void testAddMultipleInputs(int a, int b, int expected) {
        logger.info("--- ARRANGE: Input values: a={}, b={}, expected={} ---", a, b, expected);
        
        logger.info("--- ACT: Call add({}, {}) ---", a, b);
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result is {} ---", expected);
        assertEquals(expected, result);
        logger.info("✓ Parameterized test passed");
    }
    
    /**
     * Parameterized test with value source for simple cases.
     */
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 10, 100 })
    @DisplayName("Test 2: Add with zero (Value Source)")
    public void testAddWithZero(int a) {
        logger.info("--- ARRANGE: Input value: {} ---", a);
        
        logger.info("--- ACT: Call add({}, 0) ---", a);
        int result = calculatorService.add(a, 0);
        
        logger.info("--- ASSERT: Verify result is {} ---", a);
        assertEquals(a, result);
        logger.info("✓ Parameterized test passed");
    }
    
    /**
     * Parameterized test for positive numbers.
     */
    @ParameterizedTest
    @CsvSource({
        "1,2,3",
        "5,10,15",
        "100,200,300",
        "1000,2000,3000"
    })
    @DisplayName("Test 3: Add positive numbers")
    public void testAddPositiveNumbers(int a, int b, int expected) {
        logger.info("--- ARRANGE: Positive numbers: {} + {} = {} ---", a, b, expected);
        
        logger.info("--- ACT: Call add ---");
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals(expected, result);
        assertTrue(result > 0, "Result should be positive");
        logger.info("✓ Parameterized test passed");
    }
    
    /**
     * Parameterized test for edge cases.
     */
    @ParameterizedTest
    @CsvSource({
        "0,0,0",
        "-1,-1,-2",
        "2147483647,-1,2147483646",
        "-2147483648,1,-2147483647"
    })
    @DisplayName("Test 4: Add edge cases")
    public void testAddEdgeCases(int a, int b, int expected) {
        logger.info("--- ARRANGE: Edge case: {} + {} = {} ---", a, b, expected);
        
        logger.info("--- ACT: Call add ---");
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals(expected, result);
        logger.info("✓ Parameterized test passed");
    }
    
    /**
     * Parameterized test for range of values.
     */
    @ParameterizedTest
    @ValueSource(ints = { -50, -25, 0, 25, 50, 100 })
    @DisplayName("Test 5: Add with range of values")
    public void testAddRangeOfValues(int a) {
        logger.info("--- ARRANGE: Value from range: {} ---", a);
        
        logger.info("--- ACT: Call add({}, {}) ---", a, a);
        int result = calculatorService.add(a, a);
        
        logger.info("--- ASSERT: Verify result is double ---");
        assertEquals(a * 2, result);
        logger.info("✓ Parameterized test passed");
    }
    
    /**
     * Parameterized test with multiple CSV inputs.
     */
    @ParameterizedTest
    @CsvSource({
        "3,4,7",
        "5,5,10",
        "0,1,1",
        "-1,1,0",
        "999,1,1000"
    })
    @DisplayName("Test 6: Add various number combinations")
    public void testAddVariousCombinations(int a, int b, int expected) {
        logger.info("--- ARRANGE: Combination: {} + {} should equal {} ---", a, b, expected);
        
        logger.info("--- ACT: Call add ---");
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result ---");
        assertEquals(expected, result);
        assertEquals(expected, calculatorService.add(b, a), "Addition should be commutative");
        logger.info("✓ Parameterized test passed");
    }
}
