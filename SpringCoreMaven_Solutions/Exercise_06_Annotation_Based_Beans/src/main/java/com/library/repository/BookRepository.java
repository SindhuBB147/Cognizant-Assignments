package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {
    public List<String> findNewArrivals() {
        return Arrays.asList("Domain-Driven Design", "Refactoring", "Modern Java in Action");
    }
}
