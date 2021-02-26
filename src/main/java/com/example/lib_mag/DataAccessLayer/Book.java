package com.example.lib_mag.DataAccessLayer;

import javax.persistence.*;
// things which are auto generated are not passed in post calls.
@Entity // this annotation is given by hibernate to create a ORM(object relation mapping)i.e we can now all
// the objects of this class Book to our mysql DB. (IN simpler words this class become  table in mysql.)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // since here id is auto generated we don't pass id during
    private int id;                                 // post calls.
    // auto generated value if say we add 1st book to our DB then id = 1(automatically), then for next and
    // rest of the books if becomes (id=2,3,4,5...).
    // note these fields are pushed in table in asscending order starting from id then ascending order is followed
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "subject")
    private String subject;

    @Column(name = "isbn")
    private String isbn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book(String name) {
        this.title = name;
    }
}