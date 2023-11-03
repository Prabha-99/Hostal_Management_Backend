package com.example.hostal_management_b.service;


import com.example.hostal_management_b.model.Property;
import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.repository.PropertyRepo;
import com.example.hostal_management_b.repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepo propertyRepo;
    private final RoomRepo roomRepo;

    public Property addProperty(Property property) {
        int roomNo = property.getRoom_no();
        Room room = roomRepo.findById(roomNo).orElse(null);

        if (room != null) {
            room.setNo_of_items(room.getNo_of_items() + 1);
            roomRepo.save(room);
        }

        return propertyRepo.save(property);
    }
}
