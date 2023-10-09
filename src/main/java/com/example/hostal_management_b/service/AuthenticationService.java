package com.example.hostal_management_b.service;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.Academic_Registration_Request;
import com.example.hostal_management_b.dto.LoginRequest;
import com.example.hostal_management_b.dto.Student_Registration_Request;
import com.example.hostal_management_b.model.Academic_staff;
import com.example.hostal_management_b.model.Role;
import com.example.hostal_management_b.model.Student;
import com.example.hostal_management_b.repository.Academic_staff_Repo;

import com.example.hostal_management_b.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Academic_staff_Repo academicStaffRepo;
    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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

    public AuthenticationResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var academic_staff = academicStaffRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(academic_staff);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .build();
    }

    public AuthenticationResponse student_register(Student_Registration_Request request) {
            var student = Student.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .student_id(request.getStaff_id())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .room(request.getRoom())
                    .role(Role.STUDENT)
                    .build();
            studentRepo.save(student);
            var jwtToken = jwtService.generateToken(student);
            return AuthenticationResponse.builder()
                    .Token(jwtToken)
                    .build();

    }
    public AuthenticationResponse student_authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var student = studentRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(student);
        return AuthenticationResponse.builder()
                .Token(jwtToken)
                .build();
    }


}
