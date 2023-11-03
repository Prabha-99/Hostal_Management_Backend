package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.addRoom(room));
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getRoomCount() {
        long roomCount = roomService.getRoomCount();
        return ResponseEntity.ok(roomCount);
    }
}
