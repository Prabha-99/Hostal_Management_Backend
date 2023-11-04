package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.model.AcademicWardenComplains;
import com.example.hostal_management_b.model.SubWardenComplains;
import com.example.hostal_management_b.service.AcademicWardenComplainsService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/academicwardencomplains")
public class AcademicWardenComplainsController {

    private final AcademicWardenComplainsService academicWardenComplainsService;

    public AcademicWardenComplainsController(AcademicWardenComplainsService academicWardenComplainsService) {
        this.academicWardenComplainsService = academicWardenComplainsService;
    }

    @GetMapping("/all")
    public List<AcademicWardenComplains> getAllSubWardenComplains(){
        return academicWardenComplainsService.getAllAcademicWardenComplains();
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Long id) {
        AcademicWardenComplains file = academicWardenComplainsService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        // Get the file path from the ShowroomFile entity
        String filePath = file.getImagePath();
        File downloadFile = new File(filePath);

        if (!downloadFile.exists() || !downloadFile.isFile()) {
            return ResponseEntity.notFound().build();
        }
        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getImagePath());

        // Create an InputStreamResource from the file path
        InputStreamResource inputStreamResource;
        try {
            inputStreamResource = new InputStreamResource(new FileInputStream(downloadFile));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        // Stream the file content to the response
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(downloadFile.length())
                .body(inputStreamResource);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<AcademicWardenComplains> updateComplaintStatus(@RequestBody AcademicWardenComplains updatedComplaint) {
        // Use a service to update the status of the complaint in the database
        AcademicWardenComplains updated = academicWardenComplainsService.updateComplaintStatus(updatedComplaint);
        return ResponseEntity.ok(updated);
    }
}
