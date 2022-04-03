package com.example.loanapp.Exceptions;

public class UserDoesNotExist extends RuntimeException{
    public UserDoesNotExist(){
        super("User Does Not Exist");
    }
}
