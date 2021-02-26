package com.example.lib_mag.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    /* JPQL code if we dont want to write codes in controller class*/
    // creating custom fun.
    @Query("select b from Book b where b.subject=:name") // below argument name is passed in b.author
    public List<Book> getBySubject(String name); // it returns all the books which author as name = name passed
}