package com.ecommerce.RestEcommerce.controllers;

import com.ecommerce.RestEcommerce.entities.User;
import com.ecommerce.RestEcommerce.requests.LoginUserRequest;
import com.ecommerce.RestEcommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginUserRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = service.get(request.getEmail());
            service.addUserToSession(user);

            return new ResponseEntity<>(Collections.singletonMap("message", "Login Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Login Failed"), HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/logout")
    public ResponseEntity<Object> logout(@RequestParam("email") String email){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String authenticatedEmail = authentication.getName();
                if (authenticatedEmail.equals(email)) {
                    service.deleteUserFromSession(email);
                    SecurityContextHolder.clearContext();
                    return new ResponseEntity<>(Collections.singletonMap("message", "Logout Successfully"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(Collections.singletonMap("message", "Unauthorized request"), HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(Collections.singletonMap("message", "Logout Failed"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Logout Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
