package com.cognizant.junitadvanced;

/**
 * Simple Calculator class for demonstrating JUnit testing.
 * Provides basic arithmetic operations.
 */
public class Calculator {
    
    /**
     * Adds two integers.
     * 
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }
    
    /**
     * Subtracts b from a.
     * 
     * @param a first number
     * @param b second number to subtract
     * @return difference of a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }
    
    /**
     * Multiplies two integers.
     * 
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    
    /**
     * Divides a by b.
     * 
     * @param a dividend
     * @param b divisor
     * @return quotient of a divided by b
     * @throws IllegalArgumentException if b is zero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        return a / b;
    }
    
    /**
     * Checks if a number is positive.
     * 
     * @param number the number to check
     * @return true if number is greater than 0, false otherwise
     */
    public boolean isPositive(int number) {
        return number > 0;
    }
    
    /**
     * Checks if a number is negative.
     * 
     * @param number the number to check
     * @return true if number is less than 0, false otherwise
     */
    public boolean isNegative(int number) {
        return number < 0;
    }
}
