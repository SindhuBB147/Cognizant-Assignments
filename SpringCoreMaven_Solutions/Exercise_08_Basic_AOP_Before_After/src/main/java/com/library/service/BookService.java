package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String issueBook(String memberName, String title) {
        return bookRepository.issueBook(memberName, title);
    }
}
