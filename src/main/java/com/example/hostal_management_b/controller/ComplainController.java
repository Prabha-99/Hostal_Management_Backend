package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.model.Complain;
import com.example.hostal_management_b.service.ComplainService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
        // You can add validation and processing logic here
        return complainService.saveComplain(complain);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Define the directory where you want to save the uploaded files
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
}
