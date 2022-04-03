package com.example.loanapp.Controller;

import com.example.loanapp.DTO.LoginRequest;
import com.example.loanapp.DTO.LoginResponse;
import com.example.loanapp.Exceptions.WrongCredentials;
import com.example.loanapp.Security.UserDetailService;
import com.example.loanapp.Security.UserJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserJWT userJWT;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        logger.warn("User has been authenticated");
        final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername());
        logger.warn("Finding user via UserDetails");
        final String token = userJWT.generateToken(userDetails);
        logger.info("Getting UserName");
       // String getUsername = userDetails.getAuthorities().stream().findFirst().get().toString();
        String getUsername = userDetails.getUsername();
        logger.debug("Does Username Exist");
        String username = getUsername == null ? "" : getUsername;
        logger.debug("Authenticated username"+ username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginResponse(username, token));
    }



    private void authenticate(@NonNull String username, @NonNull String password) throws Exception {
        logger.info("Authenticating User");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException de) {
            logger.error("User not active");
        } catch (BadCredentialsException be) {
            logger.error("Invalid Credential " + be);
            throw new WrongCredentials();
        }
    }
}
