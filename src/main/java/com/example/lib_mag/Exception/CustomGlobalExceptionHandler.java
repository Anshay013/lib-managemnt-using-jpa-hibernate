package com.example.lib_mag.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice // annotation which allows write global code that can be applied to all types of controllers.
//whenever @ControllerAdvice is written above a class its code becomes globally accessible to all controllers and API'S.
// before client gets the response, the response goes to the class where @ControllerAdvice is.
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Let Spring handle the exception, we just override the status code
    @ExceptionHandler(UserNotFoundException.class) // UserNotFoundException is handled in here.
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
    @ExceptionHandler(BookNotFoundException.class) // BookNotFoundException is handled in here.
    public void springHandleNotFound1(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value()); // this is how we send an error.
    }// (There are various status code NOT_FOUND is one of them).
    @ExceptionHandler(BookNameEmptyException.class)
    public void springHandleNotFound2(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NO_CONTENT.value()); // this is how we send an error.
    }
    @ExceptionHandler(UserNameEmptyException.class)
    public void springHandleNotFound3(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NO_CONTENT.value()); // this is how we send an error.
    }
    @ExceptionHandler(BookNotFoundByIdException.class)
        public void springHandleNotFound4(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

}