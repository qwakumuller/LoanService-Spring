package com.example.loanapp.Repository;

import com.example.loanapp.Model.Payment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, ObjectId> {
    List<Payment> getAllByUserId(ObjectId userId);

}
