package com.example.lib_mag.Exception;

public class BookNotFoundByIdException extends RuntimeException{
    public BookNotFoundByIdException(){
        super("No books are present in DB");
    }
}
