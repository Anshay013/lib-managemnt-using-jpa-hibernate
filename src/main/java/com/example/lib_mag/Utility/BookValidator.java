package com.example.lib_mag.Utility;

import com.example.lib_mag.DataAccessLayer.Book;

public class BookValidator {

    public boolean isValid(Book book){ // function called to check whether a book is valid or not.

        if(book.getTitle()=="" || book.getTitle()==null) // i.e we check that the book at least has some title.
            return false;
        return true;
    }
}