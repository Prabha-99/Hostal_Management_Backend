package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.ComplainLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainLogRepo extends JpaRepository<ComplainLog,Long> {
}
