package com.library.repository;

public class BookRepository {
    public String findByIsbn(String isbn) {
        return "Book for ISBN " + isbn;
    }
}
