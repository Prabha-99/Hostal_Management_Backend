package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface User_Repo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAll();

    @Query(value = "SELECT COUNT(*) FROM Users WHERE room = :room", nativeQuery = true)
    int countByRoom(String room);

    @Query("SELECT u.reg_no, u.room , u.id FROM User u WHERE u.role = 'STUDENT'")
    List<Object[]> findAllStudentsRegistrationAndRoom();


}
