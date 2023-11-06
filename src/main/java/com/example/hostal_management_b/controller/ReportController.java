package com.example.hostal_management_b.controller;

import com.example.hostal_management_b.repository.ReportRepo;
import com.example.hostal_management_b.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepo reportRepo;

    @GetMapping("/dailyReport")
    public String generateDailyReport() throws JRException, FileNotFoundException{
        return reportService.exportDailyReport();
    }

    @GetMapping("/monthlyReport")
    public String generateMonthlyReport() throws JRException, FileNotFoundException{
        return reportService.exportMonthlyReport();
    }

}
