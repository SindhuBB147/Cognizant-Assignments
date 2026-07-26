package com.library.repository;

public class BookRepository {
    private String storageType;

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String describeStorage() {
        return "Books are managed using " + storageType;
    }
}
