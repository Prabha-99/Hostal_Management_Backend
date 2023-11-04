package com.example.hostal_management_b.service;

import com.example.hostal_management_b.configuration.AuthenticationResponse;
import com.example.hostal_management_b.dto.UserDto;
import com.example.hostal_management_b.dto.User_Registration_Request;
import com.example.hostal_management_b.dto.LoginRequest;
import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.model.User;
import com.example.hostal_management_b.model.Role;
import com.example.hostal_management_b.repository.User_Repo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final User_Repo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoomService roomService; // Inject RoomService

    public AuthenticationResponse register(User_Registration_Request request) {
        if ("STUDENT".equals(request.getRole())) {
            String roomNumber = request.getRoom();
            int currentOccupancy = userRepo.countByRoom(roomNumber);

            if (currentOccupancy < 4) {
                var user = User.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .reg_no(request.getReg_no())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .room(roomNumber)
                        .role(Role.STUDENT)
                        .build();

                userRepo.save(user);

                // Increment no_of_students for the assigned room
                Room room = roomService.getRoomByRoomNo(Integer.parseInt(roomNumber));
                room.setNo_of_students(room.getNo_of_students() + 1);
                roomService.updateRoom(room);

                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .Token(jwtToken)
                        .build();
            } else {
                throw new RuntimeException("Room is already full. Please choose another room.");
            }
        } else if ("ADMIN".equals(request.getRole())) {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getReg_no())
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

        } else if ("SUB_WARDEN".equals(request.getRole())) {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getReg_no())
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

        } else if ("ACADEMIC_WARDEN".equals(request.getRole())) {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getReg_no())
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

        } else if ("DEAN".equals(request.getRole())) {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .reg_no(request.getReg_no())
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
        return null;
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
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

    public List<Object[]> getAllStudentsRegistrationAndRoom() {
        return userRepo.findAllStudentsRegistrationAndRoom();
    }


    public UserDto getUserInfo(String username) { //Service to get UserInformation according to the JWT
        Optional<User> user = userRepo.findByEmail(username);
        UserDto userDto = new UserDto();
        userDto.setFirstname(user.get().getFirstname());
        userDto.setLastname(user.get().getLastname());
        userDto.setReg_no(user.get().getReg_no());
        userDto.setRoom(user.get().getRoom());
        return userDto;
    }

    public void updateRoom(int userId, String newRoomNumber) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String oldRoomNumber = user.getRoom();

        // Check if the user is actually changing rooms
        if (!oldRoomNumber.equals(newRoomNumber)) {
            // Update the user's room
            user.setRoom(newRoomNumber);
            userRepo.save(user);

            // Update the old room's no_of_students
            Room oldRoom = roomService.getRoomByRoomNo(Integer.parseInt(oldRoomNumber));
            oldRoom.setNo_of_students(oldRoom.getNo_of_students() - 1);
            roomService.updateRoom(oldRoom);

            // Update the new room's no_of_students
            Room newRoom = roomService.getRoomByRoomNo(Integer.parseInt(newRoomNumber));
            newRoom.setNo_of_students(newRoom.getNo_of_students() + 1);
            roomService.updateRoom(newRoom);
        }
    }

    public long getUserCount (){
        return userRepo.count();
    }
}
