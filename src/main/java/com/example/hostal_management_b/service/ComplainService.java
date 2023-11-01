package com.example.hostal_management_b.service;

import com.example.hostal_management_b.model.Complain;
import com.example.hostal_management_b.repository.ComplainRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainService {
    private final ComplainRepo complainRepo;

    public ComplainService(ComplainRepo complainRepo) {
        this.complainRepo = complainRepo;
    }


    public Complain saveComplain(Complain complain) {
        // You can add additional logic here if needed before saving
        return complainRepo.save(complain);
    }

    public List<Complain> getAllComplains() {
        return complainRepo.findAll();
    }
}
