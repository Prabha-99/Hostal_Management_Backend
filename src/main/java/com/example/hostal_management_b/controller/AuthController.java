package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.NavBarLogin;
import com.example.hostal_management_b.dto.UserDto;
import com.example.hostal_management_b.dto.User_Registration_Request;
import com.example.hostal_management_b.dto.LoginRequest;
import com.example.hostal_management_b.model.User;
import com.example.hostal_management_b.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/OnlineUser") //Getting Current User Information
    public ResponseEntity<UserDto> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        UserDto userDto = authenticationService.getUserInfo(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/regroom")
    public List<Object[]> getAllStudentsRegistrationAndRoom() {
        return authenticationService.getAllStudentsRegistrationAndRoom();
    }




}
