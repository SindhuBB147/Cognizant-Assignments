package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private final BookRepository bookRepository;
    private NotificationService notificationService;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerBook(String title) {
        bookRepository.save(title);
        notificationService.notifyLibrarian("A new book was registered: " + title);
    }
}
