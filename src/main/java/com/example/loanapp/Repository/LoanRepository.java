package com.example.loanapp.Repository;

import com.example.loanapp.Model.Loan;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends MongoRepository<Loan, ObjectId> {
    Optional<Loan> findLoanByUserId(ObjectId userId);
}
