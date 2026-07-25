package com.cognizant.springtest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Calculator Service for Exercise 1.
 * Provides basic arithmetic operations.
 */
@Service
public class CalculatorService {
    
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);
    
    /**
     * Adds two numbers.
     * 
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public int add(int a, int b) {
        logger.info("Adding {} and {}", a, b);
        return a + b;
    }
}
