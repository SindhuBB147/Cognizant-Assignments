package com.cognizant.junitadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup and Teardown Methods
 * 
 * This test class demonstrates the AAA pattern and test lifecycle management.
 * 
 * AAA Pattern Explanation:
 * - Arrange: Set up the test data and fixtures
 * - Act: Execute the code being tested
 * - Assert: Verify the results
 * 
 * Lifecycle Methods (JUnit 4 → JUnit 5 mapping):
 * - @Before → @BeforeEach: Runs before each test method
 * - @After → @AfterEach: Runs after each test method
 * - @BeforeClass → @BeforeAll: Runs once before all tests (static method)
 * - @AfterClass → @AfterAll: Runs once after all tests (static method)
 * 
 * Benefits of Setup/Teardown:
 * - Reduces code duplication
 * - Ensures clean state for each test
 * - Makes tests more maintainable
 * - Proper resource cleanup
 */
@DisplayName("Exercise 4: AAA Pattern, Test Fixtures, Setup and Teardown")
public class Exercise4AAAPatternTest {
    
    // Logger for demonstrating lifecycle execution
    private static final Logger logger = LoggerFactory.getLogger(Exercise4AAAPatternTest.class);
    
    // Test fixture: Calculator instance used by multiple tests
    private Calculator calculator;
    
    // Counter to track test executions (for demonstration)
    private int testCounter;
    
    /**
     * Setup method: Initializes test fixtures before each test.
     * 
     * In JUnit 4, this would be @Before
     * In JUnit 5, this is @BeforeEach
     * 
     * This method runs before EACH test method, ensuring:
     * - Fresh test data for each test
     * - No side effects from previous tests
     * - Consistent test state
     */
    @BeforeEach
    void setUp() {
        logger.info("========== SETUP: Initializing test fixtures ==========");
        
        // Arrange Phase (Part 1): Initialize test objects
        calculator = new Calculator();
        testCounter = 0;
        
        logger.info("Calculator instance created");
        logger.info("Test fixtures initialized and ready for test execution");
    }
    
    /**
     * Teardown method: Cleans up after each test.
     * 
     * In JUnit 4, this would be @After
     * In JUnit 5, this is @AfterEach
     * 
     * This method runs after EACH test method, enabling:
     * - Resource cleanup
     * - Logging test results
     * - State reset
     */
    @AfterEach
    void tearDown() {
        logger.info("========== TEARDOWN: Cleaning up after test ==========");
        logger.info("Test {} executed, calculator = {}", testCounter, calculator);
        
        // Cleanup
        calculator = null;
        logger.info("Resources cleaned up and test fixtures reset");
        logger.info("");
    }
    
    /**
     * Test 1: Addition with Positive Numbers
     * Demonstrates the AAA pattern clearly.
     */
    @Test
    @DisplayName("Test 1: Should add two positive numbers (AAA Pattern)")
    void testAddPositiveNumbersAAA() {
        testCounter = 1;
        logger.info(">>> TEST 1: Testing addition of positive numbers");
        
        // ARRANGE: Set up test data
        logger.info("ARRANGE: Setting up test data - operands: 5 and 3");
        int firstNumber = 5;
        int secondNumber = 3;
        int expectedResult = 8;
        
        // ACT: Execute the code being tested
        logger.info("ACT: Calling calculator.add({}, {})", firstNumber, secondNumber);
        int actualResult = calculator.add(firstNumber, secondNumber);
        logger.info("ACT: Result received: {}", actualResult);
        
        // ASSERT: Verify the results
        logger.info("ASSERT: Verifying result equals {}", expectedResult);
        assertEquals(expectedResult, actualResult, 
            "Addition of 5 and 3 should equal 8");
        logger.info("ASSERT: Test passed!");
    }
    
    /**
     * Test 2: Subtraction with Negative Result
     * Shows AAA pattern with negative numbers.
     */
    @Test
    @DisplayName("Test 2: Should subtract numbers resulting in negative (AAA Pattern)")
    void testSubtractNegativeResultAAA() {
        testCounter = 2;
        logger.info(">>> TEST 2: Testing subtraction with negative result");
        
        // ARRANGE: Prepare test data
        logger.info("ARRANGE: Setting up test data - operands: 3 and 5");
        int minuend = 3;
        int subtrahend = 5;
        int expectedResult = -2;
        
        // ACT: Call the method
        logger.info("ACT: Calling calculator.subtract({}, {})", minuend, subtrahend);
        int actualResult = calculator.subtract(minuend, subtrahend);
        logger.info("ACT: Result received: {}", actualResult);
        
        // ASSERT: Verify
        logger.info("ASSERT: Verifying result equals {}", expectedResult);
        assertEquals(expectedResult, actualResult,
            "Subtraction of 5 from 3 should equal -2");
        logger.info("ASSERT: Test passed!");
    }
    
    /**
     * Test 3: Multiplication
     * Demonstrates AAA with multiplication operation.
     */
    @Test
    @DisplayName("Test 3: Should multiply two numbers (AAA Pattern)")
    void testMultiplyAAA() {
        testCounter = 3;
        logger.info(">>> TEST 3: Testing multiplication");
        
        // ARRANGE
        logger.info("ARRANGE: Setting up test data - operands: 6 and 7");
        int factor1 = 6;
        int factor2 = 7;
        int expectedResult = 42;
        
        // ACT
        logger.info("ACT: Calling calculator.multiply({}, {})", factor1, factor2);
        int actualResult = calculator.multiply(factor1, factor2);
        logger.info("ACT: Result received: {}", actualResult);
        
        // ASSERT
        logger.info("ASSERT: Verifying result equals {}", expectedResult);
        assertEquals(expectedResult, actualResult,
            "Multiplication of 6 and 7 should equal 42");
        logger.info("ASSERT: Test passed!");
    }
    
    /**
     * Test 4: Division
     * AAA pattern with division operation and input validation.
     */
    @Test
    @DisplayName("Test 4: Should divide two numbers (AAA Pattern)")
    void testDivideAAA() {
        testCounter = 4;
        logger.info(">>> TEST 4: Testing division");
        
        // ARRANGE
        logger.info("ARRANGE: Setting up test data - operands: 20 and 4");
        int dividend = 20;
        int divisor = 4;
        int expectedResult = 5;
        
        // ACT
        logger.info("ACT: Calling calculator.divide({}, {})", dividend, divisor);
        int actualResult = calculator.divide(dividend, divisor);
        logger.info("ACT: Result received: {}", actualResult);
        
        // ASSERT
        logger.info("ASSERT: Verifying result equals {}", expectedResult);
        assertEquals(expectedResult, actualResult,
            "Division of 20 by 4 should equal 5");
        logger.info("ASSERT: Test passed!");
    }
    
    /**
     * Test 5: Exception Handling
     * AAA pattern with exception testing.
     */
    @Test
    @DisplayName("Test 5: Should throw exception on division by zero (AAA Pattern)")
    void testDivideByZeroAAA() {
        testCounter = 5;
        logger.info(">>> TEST 5: Testing division by zero exception handling");
        
        // ARRANGE
        logger.info("ARRANGE: Setting up test data for invalid operation");
        int dividend = 10;
        int divisor = 0;
        
        // ACT & ASSERT combined: Verify exception is thrown
        logger.info("ACT: Calling calculator.divide({}, {})", dividend, divisor);
        logger.info("ASSERT: Verifying IllegalArgumentException is thrown");
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(dividend, divisor);
        }, "Division by zero should throw IllegalArgumentException");
        logger.info("ASSERT: Exception correctly thrown and caught!");
    }
    
    /**
     * Test 6: Multiple Assertions in AAA Pattern
     * Demonstrates combining multiple assertions.
     */
    @Test
    @DisplayName("Test 6: Multiple assertions in AAA Pattern")
    void testMultipleAssertionsAAA() {
        testCounter = 6;
        logger.info(">>> TEST 6: Testing multiple operations (multiple assertions)");
        
        // ARRANGE
        logger.info("ARRANGE: Setting up test data");
        int number1 = 10;
        int number2 = 5;
        
        // ACT
        logger.info("ACT: Performing multiple calculations");
        int addResult = calculator.add(number1, number2);
        int subtractResult = calculator.subtract(number1, number2);
        int multiplyResult = calculator.multiply(number1, number2);
        
        logger.info("ACT: Results - add={}, subtract={}, multiply={}", 
            addResult, subtractResult, multiplyResult);
        
        // ASSERT: Multiple assertions
        logger.info("ASSERT: Verifying all results");
        assertEquals(15, addResult, "10 + 5 should equal 15");
        assertEquals(5, subtractResult, "10 - 5 should equal 5");
        assertEquals(50, multiplyResult, "10 * 5 should equal 50");
        assertTrue(calculator.isPositive(addResult), "Result should be positive");
        logger.info("ASSERT: All assertions passed!");
    }
    
    /**
     * Test 7: Demonstrating Test Independence
     * Shows how each test gets fresh fixtures from @BeforeEach.
     */
    @Test
    @DisplayName("Test 7: Demonstrating test independence")
    void testTestIndependenceAAA() {
        testCounter = 7;
        logger.info(">>> TEST 7: Demonstrating test independence");
        
        // ARRANGE
        logger.info("ARRANGE: This test gets a fresh Calculator instance");
        assertNotNull(calculator, "Calculator should be freshly initialized");
        
        // ACT & ASSERT
        logger.info("ACT: Performing calculation on fresh instance");
        int result = calculator.add(1, 1);
        logger.info("ASSERT: Verifying result");
        assertEquals(2, result, "Fresh calculator should work correctly");
        logger.info("ASSERT: Test independence verified!");
    }
}
