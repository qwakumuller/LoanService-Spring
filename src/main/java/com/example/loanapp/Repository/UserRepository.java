package com.example.loanapp.Repository;

import com.example.loanapp.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findUserByUserName(String username);
    void deleteByUserId(ObjectId userId);
    Optional<User> findUserByUserId(ObjectId userId);
}
