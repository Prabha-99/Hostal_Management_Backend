package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PropertyRepo extends JpaRepository<Property , String> {

    @Query(nativeQuery = true, value = "CALL GetRoomProperties(:room)")
    List<Map<String, Object>> getRoomProperties(@Param("room") int room);

}
