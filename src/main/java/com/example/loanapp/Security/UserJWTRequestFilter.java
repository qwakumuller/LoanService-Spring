package com.example.loanapp.Security;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class UserJWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserJWT employeeJWT;

    @Autowired
    private UserDetailService employeeUserDetailsService;

    @Value("${react.config.url: delete}")
    private String test;

    private static Logger logger = LoggerFactory.getLogger(UserJWTRequestFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        final String requestUrl = request.getRequestURI();
        String username = null;
        String token = null;


        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            logger.info("Token is present " +  token);
            try {
                username = employeeJWT.getUsernameFromToken(token);
            } catch (IllegalArgumentException ae) {
                logger.debug("Unable to get token");
            } catch (ExpiredJwtException ee) {
                logger.debug("Token has Expired");
            } catch (Exception e){
                logger.warn("This is the error");
                System.out.println(e);
            }
        } else {
            logger.warn("This is the URL " + requestTokenHeader);
            logger.warn("JWT does not begin with Bearer string on " + requestUrl);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.warn("username exist", username);
            UserDetails userDetails = employeeUserDetailsService.loadUserByUsername(username);

            if(employeeJWT.validate(token, userDetails)) {
                logger.info("Token valid");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ? Collections.emptyList() : userDetails.getAuthorities());
                System.out.println(userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);



    }
}
