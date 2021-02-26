package com.example.lib_mag.Controller;

import com.example.lib_mag.DataAccessLayer.Book;
import com.example.lib_mag.DataAccessLayer.BookRepository;
import com.example.lib_mag.DataAccessLayer.IssuedBooksRepository;
import com.example.lib_mag.DataAccessLayer.UserRepository;
import com.example.lib_mag.Exception.BookNameEmptyException;
import com.example.lib_mag.Exception.BookNotFoundByIdException;
import com.example.lib_mag.Exception.BookNotFoundException;
import com.example.lib_mag.Utility.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssuedBooksRepository issueRepository;

    @Autowired
    BookRepository repository;
    BookValidator validator = new BookValidator(); // object made without autowired
    /*
    @GetMapping(value = "/getBooks")
    public List<Book> getAllBook(){
        return bookRepository.findAll();
        }
     */

    // Find All books
    @GetMapping("/books")
     List<Book> findAll() { // method findAll under getAPI -->/books is called
        LOGGER.info("all books");
        // ,severe(error)
        List<Book> list = new ArrayList<Book>();
        //list = repository.findAll();
        try {
            list = repository.findAll();  // inbuilt fun in jpaRepository interface of java.sql
            if (list.size() == 0) {
                LOGGER.severe("empty_libary");
                throw new BookNotFoundException(0);

            }
        } catch (BookNotFoundException exc)

        {
            LOGGER.severe(exc.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Book Found", exc);

        }
        return list;
    }

    @PostMapping("/books")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)

     Book newBook(@RequestBody Book newBook) {
        if (validator.isValid(newBook))
            return repository.save(newBook);
        LOGGER.severe("newBook is not valid");
        // return null;
        throw new BookNameEmptyException();

    }

    @GetMapping("/bookById")
    public Book getById(@RequestParam(value = "id") int id) {
        List<Book> books = bookRepository.findAll();
        if (books.size() != 0) {
            for (Book book : books) {
                if (book.getId() == id)
                    return book;
            }
            LOGGER.info("book with this id is not present");
        } else
            throw new BookNotFoundByIdException();
        return null;


    }




    @GetMapping(value = "/searchBooksByAuthor") // we search the books by author name.
    public List<Book> searchBooksByAuthor(@RequestParam(value = "q") String author) { //q--> author's name.
        List<Book> books = bookRepository.findAll();
        ArrayList<Book> list = new ArrayList<Book>(); // it will store the book written by the given author
        for (Book book : books) { // now we scan the List "books" where all  books are stored.
            if (book.getAuthor().equals(author)) list.add(book); // if find it we add it.(jav.sql query language).
        }
        return list;
    }


    @GetMapping(value = "/searchBooksBySubject")
    public List<Book> searchBooksBySubject(@RequestParam(value = "q") String subject) {
        List<Book> books = bookRepository.findAll();
        ArrayList<Book> list = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getSubject().equals(subject)) list.add(book);
        }
        return list;
    }

    // OR instead of this function simply call-->
    /*
      public List<Book> searchBySubject(@RequestParam(value = "q") String subject){
        return getBySubject(String subject);
      }
     */
    @DeleteMapping("/deleteById")
    public boolean deleteById(@PathVariable int id) {
        List<Book> books = bookRepository.findAll();
        if (books.size() != 0) {
            bookRepository.deleteById(id);

            LOGGER.info("id deleted if found");
            return true;
        } else
            LOGGER.severe("DB was found empty");
        return false;
    }


}

