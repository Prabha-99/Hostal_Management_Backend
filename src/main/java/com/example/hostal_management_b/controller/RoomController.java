package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.addRoom(room));
    }
}
