package com.example.lib_mag.Controller;

import com.example.lib_mag.DataAccessLayer.Book;
import com.example.lib_mag.DataAccessLayer.User;
import com.example.lib_mag.DataAccessLayer.UserRepository;
import com.example.lib_mag.Exception.BookNotFoundException;
import com.example.lib_mag.Exception.UserNameEmptyException;
import com.example.lib_mag.Exception.UserNotFoundException;
import com.example.lib_mag.Utility.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    private UserRepository repository;
    UserValidator validator = new UserValidator();
    // Find
    @GetMapping("/users")
    public List<User> findAll() {
        LOGGER.info("all users");
        List<User> list = new ArrayList<User>();
        //list = repository.findAll();
        try {
            list = repository.findAll();
            if (list.size() == 0) {

                LOGGER.severe("empty_libaryofusers");
                throw new UserNotFoundException(0);
            }
        }
        catch(UserNotFoundException exc)
        {
            LOGGER.severe(exc.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User Found", exc);

        }
        return list;
    }

    @PostMapping("/users")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User newUser) {
        if(validator.isValidUser(newUser))
        return repository.save(newUser);

        LOGGER.severe("Name not valid");
         //return null; OR throw or own exception.
        throw new UserNameEmptyException();
    }

    // Find a given user
    @GetMapping("/users/{id}")
    public User findOne(@PathVariable int id){
        LOGGER.info("get_user by id" + id);
        // Optional<User> user = repository.findById(id);
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

    }
}
