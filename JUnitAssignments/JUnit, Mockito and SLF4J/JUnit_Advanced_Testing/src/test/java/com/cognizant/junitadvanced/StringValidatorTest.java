package com.cognizant.junitadvanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced test class demonstrating parameterized tests and nested test classes.
 */
@DisplayName("StringValidator Advanced Tests")
public class StringValidatorTest {
    
    private StringValidator validator = new StringValidator();
    
    @Nested
    @DisplayName("isEmpty() Tests")
    class IsEmptyTests {
        
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "\t", "\n"})
        @DisplayName("Should return true for empty or whitespace strings")
        void testEmptyStrings(String input) {
            assertTrue(validator.isEmpty(input));
        }
        
        @Test
        @DisplayName("Should return true for null string")
        void testNullString() {
            assertTrue(validator.isEmpty(null));
        }
    }
    
    @Nested
    @DisplayName("isNumeric() Tests")
    class IsNumericTests {
        
        @ParameterizedTest
        @ValueSource(strings = {"123", "0", "999"})
        @DisplayName("Should return true for numeric strings")
        void testNumericStrings(String input) {
            assertTrue(validator.isNumeric(input));
        }
        
        @ParameterizedTest
        @ValueSource(strings = {"abc", "12a", "12.5"})
        @DisplayName("Should return false for non-numeric strings")
        void testNonNumericStrings(String input) {
            assertFalse(validator.isNumeric(input));
        }
    }
}
