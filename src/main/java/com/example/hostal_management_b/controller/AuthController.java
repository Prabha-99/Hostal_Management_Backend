package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.*;
import com.example.hostal_management_b.model.User;
import com.example.hostal_management_b.repository.User_Repo;
import com.example.hostal_management_b.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final User_Repo userRepo;

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
    @PostMapping("/update-room")
    public ResponseEntity<String> updateRoom(@RequestParam int userId, @RequestParam String newRoomNumber) {
        try {
            authenticationService.updateRoom(userId, newRoomNumber);
            return ResponseEntity.ok("Room updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getRoomCount() {
        long count = authenticationService.getUserCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/students")
    public List<Map<String, Object>> getStudentInfo() {
        return authenticationService.getStudentInfo();
    }


    @GetMapping("/staff")
    public List<Map<String, Object>> getStaffInfo() {
        return authenticationService.getStaffInfo();
    }

    @GetMapping("/users")
    public List<RoomMatesDto> getUsersByRoom(@RequestParam String inputRoom) {
        return authenticationService.findRoomMates(inputRoom);
    }




}
