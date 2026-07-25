package com.cognizant.springtest.repository;

import com.cognizant.springtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User repository interface for database operations.
 * Extends JpaRepository for standard CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find users by name (custom query).
     * 
     * @param name the user's name to search for
     * @return list of users with matching name
     */
    List<User> findByName(String name);
}
