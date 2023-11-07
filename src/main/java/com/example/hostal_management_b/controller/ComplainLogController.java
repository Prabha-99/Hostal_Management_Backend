package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.model.ComplainLog;
import com.example.hostal_management_b.service.ComplainLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/complainlog")
public class ComplainLogController {

    private final ComplainLogService complainLogService;

    public ComplainLogController(ComplainLogService complainLogService) {
        this.complainLogService = complainLogService;
    }

    @GetMapping("/all")
    public List<ComplainLog> getAllComplains() {
        return complainLogService.getAllComplains();
    }
}
