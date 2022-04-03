package com.example.loanapp.Service;

import com.example.loanapp.Exceptions.UserDoesNotExist;
import com.example.loanapp.Model.User;
import com.example.loanapp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        User createUser = new User();
        createUser.setUserName(user.getUserName());
        createUser.setEmail(user.getEmail());
        createUser.setFirstName(user.getFirstName());
        createUser.setLastName(user.getLastName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(createUser);
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //TODO: Work on this later
    public void update(User user){

    }

    public void delete(ObjectId userId){
        userRepository.deleteByUserId(userId);
    }

    public User findUserById(ObjectId userId) {
        Optional<User> userOptional = userRepository.findUserByUserId(userId);
        if(userOptional.isEmpty()){
            throw new UserDoesNotExist();
        }
        return userOptional.get();
    }
}
