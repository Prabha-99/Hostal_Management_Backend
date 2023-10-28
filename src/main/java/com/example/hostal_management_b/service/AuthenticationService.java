package com.example.hostal_management_b.service;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.User_Registration_Request;
import com.example.hostal_management_b.dto.LoginRequest;
import com.example.hostal_management_b.model.User;
import com.example.hostal_management_b.model.Role;
import com.example.hostal_management_b.repository.User_Repo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final User_Repo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User_Registration_Request request){
        if ("ADMIN".equals(request.getRole())){
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .room("NULL")
                    .role(Role.ADMIN)
                    .build();
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        else if ("SUB_WARDEN".equals(request.getRole())){
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .room("NULL")
                    .role(Role.SUB_WARDEN)
                    .build();
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        else if ("ACADEMIC_WARDEN".equals(request.getRole())){
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .room("NULL")
                    .role(Role.ACADEMIC_WARDEN)
                    .build();
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        else if ("DEAN".equals(request.getRole())){
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .room("NULL")
                    .role(Role.DEAN)
                    .build();
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        else if ("STUDENT".equals(request.getRole())){
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .room(request.getRoom())
                    .role(Role.STUDENT)
                    .build();
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        return null;

    }

    public AuthenticationResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var academic_staff = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(academic_staff);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .build();
    }





}
