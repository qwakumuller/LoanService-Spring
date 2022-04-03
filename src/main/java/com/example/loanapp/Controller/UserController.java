package com.example.loanapp.Controller;

import com.example.loanapp.DTO.GetAllPaymentRequest;
import com.example.loanapp.Model.User;
import com.example.loanapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/start")
    public ResponseEntity start(){
        return ResponseEntity.status(HttpStatus.OK).body("Welcome To Loan Application");
    }



    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody GetAllPaymentRequest deleteUser){
        userService.delete(deleteUser.getUserId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/getUser")
    public ResponseEntity getUser(@RequestBody GetAllPaymentRequest findUser){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(findUser.getUserId()));
    }
}
