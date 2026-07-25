package com.cognizant.springtest.controller;

import com.cognizant.springtest.entity.User;
import com.cognizant.springtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User REST Controller for Exercises 3-5 and 8.
 * Handles HTTP requests for user operations.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * GET endpoint to retrieve a user by ID (Exercise 3).
     * 
     * @param id the user's ID
     * @return ResponseEntity with the user
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        logger.info("GET /users/{} - Fetching user", id);
        User user = userService.getUserById(id);
        return user != null 
            ? ResponseEntity.ok(user)
            : ResponseEntity.notFound().build();
    }
    
    /**
     * GET endpoint to retrieve all users.
     * 
     * @return ResponseEntity with list of all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("GET /users - Fetching all users");
        // This would require findAll() in UserService
        return ResponseEntity.ok(List.of());
    }
    
    /**
     * POST endpoint to create a new user (Exercise 5).
     * 
     * @param user the user to create
     * @return ResponseEntity with the created user
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("POST /users - Creating user: {}", user.getName());
        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }
    
    /**
     * PUT endpoint to update a user (Exercise 5).
     * 
     * @param id the user's ID
     * @param user the updated user data
     * @return ResponseEntity with the updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("PUT /users/{} - Updating user", id);
        User updatedUser = userService.updateUser(id, user);
        return updatedUser != null 
            ? ResponseEntity.ok(updatedUser)
            : ResponseEntity.notFound().build();
    }
    
    /**
     * DELETE endpoint to delete a user.
     * 
     * @param id the user's ID
     * @return ResponseEntity indicating success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("DELETE /users/{} - Deleting user", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    
    /**
     * GET endpoint to search users by name (Exercise 7).
     * 
     * @param name the user's name to search for
     * @return ResponseEntity with list of matching users
     */
    @GetMapping("/search/by-name")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
        logger.info("GET /users/search/by-name?name={} - Searching users", name);
        List<User> users = userService.getUsersByName(name);
        return ResponseEntity.ok(users);
    }
}
