package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.NavBarLogin;
import com.example.hostal_management_b.dto.User_Registration_Request;
import com.example.hostal_management_b.dto.LoginRequest;
import com.example.hostal_management_b.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody User_Registration_Request request){
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @PostMapping("/login")
    public  ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


    @GetMapping("/CurrentUser")
    public NavBarLogin getCurrentUser(){  //Getting the CurrentUser username Using Authentication Interface that comes with Spring Security
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String firstname=authentication.getName();
        return new NavBarLogin(firstname);
    }




}
