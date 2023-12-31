package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.dto.PropertyDto;
import com.example.hostal_management_b.dto.RoomMatesDto;
import com.example.hostal_management_b.model.Property;
import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping("/add")
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property addedProperty = propertyService.addProperty(property);
        return new ResponseEntity<>(addedProperty, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Property> getAllItems() {
        return propertyService.getAllItems();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getRoomCount() {
        long count = propertyService.getCountItems();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/property")
    public List<PropertyDto> getUsersByRoom(@RequestParam int inputRoom) {
        return propertyService.findProperty(inputRoom);
    }
}
