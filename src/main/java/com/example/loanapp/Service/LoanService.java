package com.example.loanapp.Service;

import com.example.loanapp.Exceptions.LoanDoesNotExist;
import com.example.loanapp.Model.Loan;
import com.example.loanapp.Repository.LoanRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private static Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private LoanRepository loanRepository;

    /**
     * This is to be used by only ADMINS
     * @param loan
     */
    public void createLoan(Loan loan){
        logger.warn("Loan Creation is about to Start");
        Loan newLoan = new Loan();
        newLoan.setTotalAmountPaid(loan.getTotalAmountPaid());
        newLoan.setTotalAmountLeft(loan.getTotalAmountLeft());
        newLoan.setTotalLoanAmount(loan.getTotalLoanAmount());
        newLoan.setUserId(loan.getUserId());
        logger.warn("Loan Creation Done, information saved into the Database");

        loanRepository.save(newLoan);

    }

    /**
     * This is to be used by ADMIN only
     */
    public List<Loan> getAllLoans(){
        logger.warn("Request for All Loans in the Database has been Requested");
        return loanRepository.findAll();
    }

    public Loan getLoan(ObjectId userId){
        Optional<Loan> optionalLoan = loanRepository.findLoanByUserId(userId);
        if(optionalLoan.isEmpty()){
            logger.warn("Loan Does Not Exist In the Database");
            throw new LoanDoesNotExist();
        }
        logger.info("Loan Exist in the Database, Data is Sent");
        return optionalLoan.get();
    }
}
