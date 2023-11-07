package com.example.hostal_management_b.service;

import com.example.hostal_management_b.model.Complain;
import com.example.hostal_management_b.model.ComplainLog;
import com.example.hostal_management_b.repository.ComplainLogRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainLogService {

    private final ComplainLogRepo complainLogRepo;

    public ComplainLogService(ComplainLogRepo complainLogRepo) {
        this.complainLogRepo = complainLogRepo;
    }

    public List<ComplainLog> getAllComplains() {
        return complainLogRepo.findAll();
    }
}
