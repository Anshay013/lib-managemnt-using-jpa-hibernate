package com.example.lib_mag.Exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int id) {
        super("Book id not found : " + id);
    }
}