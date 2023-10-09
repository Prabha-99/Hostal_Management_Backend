package com.example.hostal_management_b.service;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.Academic_Registration_Request;
import com.example.hostal_management_b.model.Academic_staff;
import com.example.hostal_management_b.model.Role;
import com.example.hostal_management_b.repository.Academic_staff_Repo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Academic_staff_Repo academicStaffRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(Academic_Registration_Request request){
        if ("ADMIN".equals(request.getRole())){
            var academic_staff = Academic_staff.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .staff_id(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();
            academicStaffRepo.save(academic_staff);
            var jwtToken = jwtService.generateToken(academic_staff);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        else if ("SUB_WARDEN".equals(request.getRole())){
            var academic_staff = Academic_staff.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .staff_id(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.SUB_WARDEN)
                    .build();
            academicStaffRepo.save(academic_staff);
            var jwtToken = jwtService.generateToken(academic_staff);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        else if ("ACADEMIC_WARDEN".equals(request.getRole())){
            var academic_staff = Academic_staff.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .staff_id(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ACADEMIC_WARDEN)
                    .build();
            academicStaffRepo.save(academic_staff);
            var jwtToken = jwtService.generateToken(academic_staff);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        else if ("DEAN".equals(request.getRole())){
            var academic_staff = Academic_staff.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .staff_id(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.DEAN)
                    .build();
            academicStaffRepo.save(academic_staff);
            var jwtToken = jwtService.generateToken(academic_staff);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();
        }
        return null;

    }

}
