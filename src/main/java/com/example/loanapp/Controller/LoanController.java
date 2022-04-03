package com.example.loanapp.Controller;

import com.example.loanapp.DTO.GetAllPaymentRequest;
import com.example.loanapp.Model.Loan;
import com.example.loanapp.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loan")
@CrossOrigin(origins="*")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/createLoan")
    public ResponseEntity createLoan(@RequestBody Loan loan){
        loanService.createLoan(loan);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllLoans(){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getAllLoans());
    }

    @PostMapping("/getLoan")
    public ResponseEntity getLoan(@RequestBody GetAllPaymentRequest getUserLoan){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getLoan(getUserLoan.getUserId()));
    }
}
