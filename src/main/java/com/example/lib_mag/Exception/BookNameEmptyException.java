package com.example.lib_mag.Exception;

public class BookNameEmptyException extends RuntimeException{

    public BookNameEmptyException(){
        super("Book Name Missing");
    }
}
