package com.cognizant.springtest.entity;

import jakarta.persistence.*;

/**
 * User entity for Spring Testing exercises.
 * Represents a user with ID and name.
 */
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    /**
     * Default constructor.
     */
    public User() {
    }
    
    /**
     * Constructor with name parameter.
     * 
     * @param name the user's name
     */
    public User(String name) {
        this.name = name;
    }
    
    /**
     * Constructor with id and name.
     * 
     * @param id the user's ID
     * @param name the user's name
     */
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (id != null ? id.equals(user.id) : user.id == null) &&
               (name != null ? name.equals(user.name) : user.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
