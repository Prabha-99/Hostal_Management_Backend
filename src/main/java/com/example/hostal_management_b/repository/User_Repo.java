package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface User_Repo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAll();

}
