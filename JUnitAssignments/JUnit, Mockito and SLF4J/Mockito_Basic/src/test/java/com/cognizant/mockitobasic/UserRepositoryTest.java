package com.cognizant.mockitobasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Basic Mockito test demonstrating mocking and stubbing.
 */
@DisplayName("UserRepository Mockito Tests")
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    
    @Mock
    private UserRepository userRepository;
    
    private User testUser;
    
    @BeforeEach
    void setUp() {
        // Arrange: Create test user
        testUser = new User("1", "John Doe", "john@example.com");
    }
    
    @Test
    @DisplayName("Should find user by ID using mock")
    void testFindUserById() {
        // Arrange: Configure mock behavior
        when(userRepository.findById("1")).thenReturn(testUser);
        
        // Act: Call the mock
        User result = userRepository.findById("1");
        
        // Assert: Verify result and mock interaction
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).findById("1");
    }
    
    @Test
    @DisplayName("Should save user successfully")
    void testSaveUser() {
        // Arrange: Configure mock to return true
        when(userRepository.save(testUser)).thenReturn(true);
        
        // Act
        boolean result = userRepository.save(testUser);
        
        // Assert
        assertTrue(result);
        verify(userRepository).save(testUser);
    }
}
