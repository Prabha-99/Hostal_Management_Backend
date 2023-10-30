package com.example.hostal_management_b.service;

import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepo roomRepo;

    public Room addRoom (Room room){
        return roomRepo.save(room);
    }

    public Room getRoomByRoomNo(int roomNo) {
        return roomRepo.findById(roomNo)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Room updateRoom(Room room) {
        return roomRepo.save(room);
    }
}
