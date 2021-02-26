package com.example.lib_mag.Exception;

public class UserNameEmptyException extends  RuntimeException {

     public UserNameEmptyException(){
         super("user name missing");
     }
}
