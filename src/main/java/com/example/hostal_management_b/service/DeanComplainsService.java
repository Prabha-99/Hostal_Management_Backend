package com.example.hostal_management_b.service;

import com.example.hostal_management_b.model.AcademicWardenComplains;
import com.example.hostal_management_b.model.DeanComplains;
import com.example.hostal_management_b.repository.DeanComplainsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeanComplainsService {

    private final DeanComplainsRepo deanComplainsRepo;

    public DeanComplainsService(DeanComplainsRepo deanComplainsRepo) {
        this.deanComplainsRepo = deanComplainsRepo;
    }

    public List<DeanComplains> getAllAcademicWardenComplains() {
        return deanComplainsRepo.findAll();
    }

    public DeanComplains getFileById(Long id) {
        return deanComplainsRepo.findById(id).orElse(null);
    }

    public DeanComplains updateComplaintStatus(DeanComplains updatedComplaint) {
        // Update the status of the complaint in the database
        updatedComplaint.setStatus("Accepted");
        return deanComplainsRepo.save(updatedComplaint);
    }

}
