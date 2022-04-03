package com.example.loanapp.Security;

import com.example.loanapp.Model.User;
import com.example.loanapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> getUser = userRepository.findUserByUserName(username);
        if(getUser.isEmpty()){
            throw new UsernameNotFoundException("User Does Not Exist");
        }


        return new UserDetail(getUser.get());
    }

}
