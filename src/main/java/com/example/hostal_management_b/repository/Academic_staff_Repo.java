package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.Academic_staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Academic_staff_Repo extends JpaRepository<Academic_staff , Integer> {
    Optional<Academic_staff> findByEmail(String email);
    List<Academic_staff> findAll();

}
