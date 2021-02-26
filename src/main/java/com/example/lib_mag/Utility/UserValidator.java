package com.example.lib_mag.Utility;

import com.example.lib_mag.DataAccessLayer.User;

public class UserValidator {

    public boolean isValidUser(User user){
        if(user.getName()=="" || user.getName()==null){
            return false;}
                return true;
        }
    }

