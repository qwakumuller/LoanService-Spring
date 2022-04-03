package com.example.loanapp.Exceptions;

public class WrongCredentials extends RuntimeException{
    public WrongCredentials(){
        super("Wrong Credentials");
    }
}
