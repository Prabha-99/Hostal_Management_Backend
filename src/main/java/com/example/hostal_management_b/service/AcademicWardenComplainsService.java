package com.example.hostal_management_b.service;

import com.example.hostal_management_b.model.AcademicWardenComplains;
import com.example.hostal_management_b.repository.AcademicWardenComplainsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicWardenComplainsService {

    private final AcademicWardenComplainsRepo academicWardenComplainsRepo;

    public AcademicWardenComplainsService(AcademicWardenComplainsRepo academicWardenComplainsRepo) {
        this.academicWardenComplainsRepo = academicWardenComplainsRepo;
    }

    public List<AcademicWardenComplains> getAllAcademicWardenComplains() {
        return academicWardenComplainsRepo.findAll();
    }

    public AcademicWardenComplains getFileById(Long id) {
        return academicWardenComplainsRepo.findById(id).orElse(null);
    }

    public AcademicWardenComplains updateComplaintStatus(AcademicWardenComplains updatedComplaint) {
        // Update the status of the complaint in the database
        updatedComplaint.setStatus("Accepted");
        return academicWardenComplainsRepo.save(updatedComplaint);
    }
}
