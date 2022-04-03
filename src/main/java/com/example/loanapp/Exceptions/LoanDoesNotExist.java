package com.example.loanapp.Exceptions;

public class LoanDoesNotExist extends RuntimeException {
    public LoanDoesNotExist(){
        super("Loan Does Not Exist");
    }
}
