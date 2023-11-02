package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainRepo extends JpaRepository <Complain,Long>{
}
