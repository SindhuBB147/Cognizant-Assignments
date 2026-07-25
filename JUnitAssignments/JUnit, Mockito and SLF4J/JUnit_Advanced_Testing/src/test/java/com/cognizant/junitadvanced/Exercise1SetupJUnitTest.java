package com.cognizant.junitadvanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 1: Setting Up JUnit
 * 
 * This test class demonstrates the basic setup of JUnit 5.
 * JUnit 5 (Jupiter) is configured in the pom.xml with:
 * - junit-jupiter-api (for annotations)
 * - junit-jupiter-engine (test execution engine)
 * - junit-jupiter-params (for parameterized tests)
 * 
 * Annotation Mapping (JUnit 4 → JUnit 5):
 * - @Test → @Test (same)
 * - @Before → @BeforeEach
 * - @After → @AfterEach
 * - @BeforeClass → @BeforeAll
 * - @AfterClass → @AfterAll
 */
@DisplayName("Exercise 1: Setting Up JUnit")
public class Exercise1SetupJUnitTest {
    
    /**
     * Simple test to verify JUnit is properly configured.
     * This test should always pass if JUnit is set up correctly.
     */
    @Test
    @DisplayName("Should verify JUnit is properly set up")
    void testJUnitSetup() {
        // Arrange & Act: Create a simple object
        Calculator calculator = new Calculator();
        
        // Assert: Verify the object was created successfully
        assertNotNull(calculator, "Calculator should be instantiated");
    }
    
    /**
     * Demonstrates that test methods are discovered and executed.
     */
    @Test
    @DisplayName("Should discover and execute test methods")
    void testMethodDiscovery() {
        // Simple assertion to show test execution
        assertTrue(true, "This test demonstrates method discovery");
    }
    
    /**
     * Shows that multiple tests can be executed in sequence.
     */
    @Test
    @DisplayName("Should execute multiple tests independently")
    void testMultipleTestExecution() {
        // Each test runs independently
        int result = 2 + 3;
        assertEquals(5, result);
    }
}
