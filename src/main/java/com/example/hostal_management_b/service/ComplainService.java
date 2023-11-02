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
        return complainRepo.save(complain);
    }

    public List<Complain> getAllComplains() {
        return complainRepo.findAll();
    }

    public Complain getFileById(Long id) {
        return complainRepo.findById(id).orElse(null);
    }

    public Complain updateComplaintStatus(Complain updatedComplaint) {
        // Update the status of the complaint in the database
        updatedComplaint.setStatus("Accepted");
        return complainRepo.save(updatedComplaint);
    }
}
