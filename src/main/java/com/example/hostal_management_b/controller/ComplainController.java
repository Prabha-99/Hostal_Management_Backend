package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.model.Complain;
import com.example.hostal_management_b.service.ComplainService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/complain")
public class ComplainController {

    private final ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @PostMapping("/submit")
    public Complain submitComplain(@RequestBody Complain complain) {
        return complainService.saveComplain(complain);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDirectory = "F:\\Uni Works\\Level 3\\Sem 2\\ADBMS\\Group_Project\\files";

            // Get the original file name
            String originalFileName = file.getOriginalFilename();

            // Create a unique file name to avoid overwriting existing files
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            // Build the path to save the file
            Path filePath = Paths.get(uploadDirectory, uniqueFileName);

            // Copy the file to the server
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the file path as a string
            return uploadDirectory + uniqueFileName;
        } catch (IOException e) {
            // Handle the exception, e.g., log an error or return an error message
            return "Error uploading the file";
        }
    }



    @GetMapping("/all")
    public List<Complain> getAllComplains() {
        return complainService.getAllComplains();
    }

    //File Download Endpoint
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Long id) {
        Complain file = complainService.getFileById(id);
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
    public ResponseEntity<Complain> updateComplaintStatus(@RequestBody Complain updatedComplaint) {
        // Use a service to update the status of the complaint in the database
        Complain updated = complainService.updateComplaintStatus(updatedComplaint);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/accepted")
    public List<Map<String, Object>> getAcceptedComplainInfo() {
        return complainService.getAcceptedComplainInfo();
    }

    @GetMapping("/null")
    public List<Map<String, Object>> getNullComplainInfo() {
        return complainService.getNullComplainInfo();
    }

}
