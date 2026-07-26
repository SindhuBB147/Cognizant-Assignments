package com.library.repository;

public class BookRepository {
    public String issueBook(String memberName, String title) {
        return title + " issued to " + memberName;
    }
}
