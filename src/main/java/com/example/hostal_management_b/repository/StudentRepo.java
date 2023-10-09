package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student , Integer> {
    Optional<Student> findByEmail(String email);
    List<Student> findAll();
}
