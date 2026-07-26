package com.library.repository;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    public List<String> findFeaturedBooks() {
        return Arrays.asList("Clean Code", "Spring in Action", "Head First Java");
    }
}
