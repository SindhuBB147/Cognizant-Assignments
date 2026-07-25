package com.cognizant.junitadvanced;

/**
 * Placeholder class for JUnit Advanced Testing module.
 */
public class StringValidator {
    
    /**
     * Validates if a string is empty or null.
     * 
     * @param str the string to validate
     * @return true if string is empty or null, false otherwise
     */
    public boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Validates if a string contains only digits.
     * 
     * @param str the string to validate
     * @return true if string contains only digits, false otherwise
     */
    public boolean isNumeric(String str) {
        if (isEmpty(str)) return false;
        return str.matches("\\d+");
    }
}
