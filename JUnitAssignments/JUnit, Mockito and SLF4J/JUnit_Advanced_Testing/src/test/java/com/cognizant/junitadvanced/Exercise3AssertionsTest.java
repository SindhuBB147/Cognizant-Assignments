package com.cognizant.junitadvanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 3: Assertions in JUnit
 * 
 * This test class demonstrates various JUnit assertions available for validating test results.
 * Assertions are the core of unit testing - they verify expected vs actual outcomes.
 * 
 * Common JUnit 5 Assertions:
 * - assertEquals(expected, actual) - Checks if two values are equal
 * - assertTrue(condition) - Checks if condition is true
 * - assertFalse(condition) - Checks if condition is false
 * - assertNull(object) - Checks if object is null
 * - assertNotNull(object) - Checks if object is not null
 * - assertThrows(exceptionType, executable) - Checks if exception is thrown
 * - assertArrayEquals(expected, actual) - Checks if arrays are equal
 * - assertSame(expected, actual) - Checks if objects are identical (same reference)
 * - assertNotSame(expected, actual) - Checks if objects are different
 */
@DisplayName("Exercise 3: Assertions in JUnit")
public class Exercise3AssertionsTest {
    
    /**
     * Comprehensive test demonstrating various JUnit assertions.
     * This matches the solution code from the assignment but adapted for JUnit 5.
     */
    @Test
    @DisplayName("Should demonstrate various JUnit assertions")
    void testAssertions() {
        // Assert equals: Verify that two values are equal
        assertEquals(5, 2 + 3, "2 + 3 should equal 5");
        assertEquals("Hello", "Hel" + "lo", "String concatenation should work");
        
        // Assert true: Verify that a condition is true
        assertTrue(5 > 3, "5 should be greater than 3");
        assertTrue(10 % 2 == 0, "10 is even, so modulo 2 should be 0");
        
        // Assert false: Verify that a condition is false
        assertFalse(5 < 3, "5 should not be less than 3");
        assertFalse(10 % 2 == 1, "10 is even, so modulo 2 should not be 1");
        
        // Assert null: Verify that an object is null
        String nullString = null;
        assertNull(nullString, "nullString should be null");
        
        // Assert not null: Verify that an object is not null
        Object obj = new Object();
        assertNotNull(obj, "Created object should not be null");
    }
    
    /**
     * Test assertEquals with different data types.
     */
    @Test
    @DisplayName("Should verify assertEquals works with different types")
    void testAssertEquals() {
        // Integer comparison
        assertEquals(10, 5 + 5);
        
        // Double comparison with delta for floating point precision
        assertEquals(0.1 + 0.2, 0.3, 0.0001, 
            "Floating point comparison should account for precision");
        
        // String comparison
        assertEquals("JUnit", "JU" + "nit");
        
        // Boolean comparison
        assertEquals(true, 5 > 3);
    }
    
    /**
     * Test assertTrue and assertFalse assertions.
     */
    @Test
    @DisplayName("Should verify boolean assertions")
    void testBooleanAssertions() {
        Calculator calculator = new Calculator();
        
        // assertTrue: Verify positive number detection
        assertTrue(calculator.isPositive(5), "5 should be positive");
        
        // assertFalse: Verify negative number detection
        assertFalse(calculator.isPositive(-5), "-5 should not be positive");
        
        // More complex boolean assertions
        assertTrue((10 > 5) && (3 < 10), "Complex boolean expression should be true");
        assertFalse((10 < 5) || (3 > 10), "Complex boolean expression should be false");
    }
    
    /**
     * Test assertNull and assertNotNull assertions.
     */
    @Test
    @DisplayName("Should verify null assertions")
    void testNullAssertions() {
        // Assert null
        String nullReference = null;
        assertNull(nullReference, "Null reference should be null");
        
        // Assert not null
        String nonNullReference = "Hello";
        assertNotNull(nonNullReference, "Non-null reference should not be null");
        
        // Assert not null with object creation
        assertNotNull(new Calculator(), "New calculator instance should not be null");
    }
    
    /**
     * Test assertThrows: Verify that expected exceptions are thrown.
     */
    @Test
    @DisplayName("Should verify exception throwing with assertThrows")
    void testAssertThrows() {
        Calculator calculator = new Calculator();
        
        // Verify that IllegalArgumentException is thrown when dividing by zero
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        }, "Dividing by zero should throw IllegalArgumentException");
    }
    
    /**
     * Test assertSame and assertNotSame: Verify object identity (reference equality).
     */
    @Test
    @DisplayName("Should verify object identity assertions")
    void testIdentityAssertions() {
        // assertSame: Same object reference
        String str1 = "test";
        String str2 = str1;
        assertSame(str1, str2, "str1 and str2 should reference the same object");
        
        // assertNotSame: Different object references (even with same content)
        String str3 = new String("test");
        String str4 = new String("test");
        assertNotSame(str3, str4, "Different String objects should not be the same reference");
        assertEquals(str3, str4, "But they should be equal in content");
    }
    
    /**
     * Test assertArrayEquals: Verify array equality.
     */
    @Test
    @DisplayName("Should verify array equality assertions")
    void testArrayAssertions() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual = {1, 2, 3, 4, 5};
        
        assertArrayEquals(expected, actual, "Arrays should be equal");
    }
    
    /**
     * Test combining multiple assertions in one test.
     */
    @Test
    @DisplayName("Should combine multiple assertions effectively")
    void testMultipleAssertions() {
        Calculator calculator = new Calculator();
        
        // All assertions must pass for test to pass
        assertEquals(8, calculator.add(5, 3));
        assertEquals(2, calculator.subtract(5, 3));
        assertEquals(15, calculator.multiply(5, 3));
        assertNotNull(calculator, "Calculator should not be null");
        assertTrue(calculator.isPositive(10), "10 should be positive");
        assertFalse(calculator.isNegative(10), "10 should not be negative");
    }
}
