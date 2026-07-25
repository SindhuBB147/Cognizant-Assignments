package com.cognizant.springtest;

import com.cognizant.springtest.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 1: Basic Unit Test for a Service Method
 * 
 * Tests the CalculatorService.add() method
 */
@SpringBootTest
@DisplayName("Exercise 1: Basic Unit Test for a Service Method")
public class Exercise1CalculatorServiceTest {
    
    private static final Logger logger = LoggerFactory.getLogger(Exercise1CalculatorServiceTest.class);
    
    @Autowired
    private CalculatorService calculatorService;
    
    @BeforeEach
    public void setUp() {
        logger.info("=== SETUP: Initialize CalculatorService ===");
    }
    
    /**
     * Test adding two positive numbers.
     */
    @Test
    @DisplayName("Test 1: Add two positive numbers")
    public void testAdd() {
        logger.info("--- ARRANGE: Two positive numbers ---");
        int a = 5;
        int b = 3;
        
        logger.info("--- ACT: Call add({}, {})", a, b);
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result is 8 ---");
        assertEquals(8, result);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test adding negative numbers.
     */
    @Test
    @DisplayName("Test 2: Add negative numbers")
    public void testAddNegative() {
        logger.info("--- ARRANGE: Two negative numbers ---");
        int a = -5;
        int b = -3;
        
        logger.info("--- ACT: Call add(-5, -3) ---");
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result is -8 ---");
        assertEquals(-8, result);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test adding positive and negative numbers.
     */
    @Test
    @DisplayName("Test 3: Add positive and negative numbers")
    public void testAddMixed() {
        logger.info("--- ARRANGE: Positive and negative numbers ---");
        int a = 10;
        int b = -3;
        
        logger.info("--- ACT: Call add(10, -3) ---");
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result is 7 ---");
        assertEquals(7, result);
        logger.info("✓ Test passed");
    }
    
    /**
     * Test adding with zero.
     */
    @Test
    @DisplayName("Test 4: Add with zero")
    public void testAddWithZero() {
        logger.info("--- ARRANGE: Number and zero ---");
        int a = 5;
        int b = 0;
        
        logger.info("--- ACT: Call add(5, 0) ---");
        int result = calculatorService.add(a, b);
        
        logger.info("--- ASSERT: Verify result is 5 ---");
        assertEquals(5, result);
        logger.info("✓ Test passed");
    }
}
