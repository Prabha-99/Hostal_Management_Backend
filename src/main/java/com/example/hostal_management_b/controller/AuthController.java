package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.User_Registration_Request;
import com.example.hostal_management_b.dto.LoginRequest;
import com.example.hostal_management_b.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
