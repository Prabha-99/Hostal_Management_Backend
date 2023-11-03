package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepo extends JpaRepository<Property , String> {

}
