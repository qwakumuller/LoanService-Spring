package com.example.loanapp.Service;

import com.example.loanapp.Model.Loan;
import com.example.loanapp.Model.Payment;
import com.example.loanapp.Repository.LoanRepository;
import com.example.loanapp.Repository.PaymentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LoanRepository loanRepository;

    public void makePayment(Payment payment){
        Payment newPayment = new Payment();
        newPayment.setAmountPaid(payment.getAmountPaid());
        newPayment.setDatePaid(new Date());
        newPayment.setUserId(payment.getUserId());
        updateLoan(payment.getUserId(), payment.getAmountPaid());

        paymentRepository.save(newPayment);

    }

    public void updateLoan(ObjectId userId, float amountPaid){
        Optional<Loan> optionalLoan = loanRepository.findLoanByUserId(userId);
        if(optionalLoan.isPresent()){
            Loan loan = optionalLoan.get();
           float totalAmountPaid =  loan.getTotalAmountPaid()+amountPaid;
           float totalAmountLeft = loan.getTotalAmountLeft()-amountPaid;

           loan.setTotalAmountLeft(totalAmountLeft);
           loan.setTotalAmountPaid(totalAmountPaid);

           loanRepository.save(loan);

        }



    }

    public List<Payment> getAllPayments( ObjectId userId){
        return paymentRepository.getAllByUserId(userId);
    }
}
