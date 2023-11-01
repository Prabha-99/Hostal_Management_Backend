package com.example.hostal_management_b.repository;

import com.example.hostal_management_b.model.QRCodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeInfoRepo extends JpaRepository<QRCodeInfo,Long> {
}
