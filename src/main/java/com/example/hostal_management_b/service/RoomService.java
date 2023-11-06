package com.example.hostal_management_b.service;

import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepo roomRepo;
    private final JdbcTemplate jdbcTemplate;

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

    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    public long getRoomCount() {
        String sql = "SELECT GetRoomCount()";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

}
