package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.SubWardenComplains;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WCListRepo extends JpaRepository<SubWardenComplains,Long> {
}
