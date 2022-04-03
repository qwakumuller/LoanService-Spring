package com.example.loanapp.Controller;

import com.example.loanapp.DTO.GetAllPaymentRequest;
import com.example.loanapp.Model.Payment;
import com.example.loanapp.Service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makepayment")
    public ResponseEntity makePayment(@RequestBody Payment payment){
        logger.info("Payment Request has been Initiated");
        paymentService.makePayment(payment);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping("/getpayment")
    public ResponseEntity getAllPayments(@RequestBody GetAllPaymentRequest getAllPaymentRequest){
        logger.info("Get all Payment has been Initiated by " + getAllPaymentRequest.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAllPayments(getAllPaymentRequest.getUserId()));
    }
}
