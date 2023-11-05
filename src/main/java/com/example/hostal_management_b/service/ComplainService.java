package com.example.hostal_management_b.service;

import com.example.hostal_management_b.model.Complain;
import com.example.hostal_management_b.repository.ComplainRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComplainService {
    private final ComplainRepo complainRepo;
    private final JdbcTemplate jdbcTemplate;

    public ComplainService(ComplainRepo complainRepo, JdbcTemplate jdbcTemplate) {
        this.complainRepo = complainRepo;
        this.jdbcTemplate = jdbcTemplate;
    }


    public Complain saveComplain(Complain complain) {
        // Save the complain using the repository
        Complain savedComplain = complainRepo.save(complain);

        // Call the procedure 'CALL UpdatePropertyStatus();'
        jdbcTemplate.execute("CALL UpdatePropertyStatus();");

        return savedComplain;
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
        Complain savedComplaint = complainRepo.save(updatedComplaint);

        // Call the procedure 'CALL UpdatePropertyStatus();'
        jdbcTemplate.execute("CALL UpdatePropertyStatus();");

        return savedComplaint;
    }

    public List<Map<String, Object>> getAcceptedComplainInfo() {
        String sql = "SELECT * FROM AcceptedComplains";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getNullComplainInfo() {
        String sql = "SELECT * FROM NullComplains";
        return jdbcTemplate.queryForList(sql);
    }
}
