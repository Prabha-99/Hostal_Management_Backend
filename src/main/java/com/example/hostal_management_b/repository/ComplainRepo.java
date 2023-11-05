package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainRepo extends JpaRepository <Complain,Long>{

    @Query(value = "SELECT * FROM complain WHERE reg_no = ?1", nativeQuery = true)
    List<Complain> findUsersByEmail(String email);
}
