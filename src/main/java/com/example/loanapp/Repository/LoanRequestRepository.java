package com.example.loanapp.Repository;

import com.example.loanapp.Model.LoanRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends MongoRepository<LoanRequest, ObjectId> {
}
