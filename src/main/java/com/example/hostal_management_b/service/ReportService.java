package com.example.hostal_management_b.service;


import com.example.hostal_management_b.model.Complain;
import com.example.hostal_management_b.model.ComplainLog;
import com.example.hostal_management_b.model.DeanComplains;
import com.example.hostal_management_b.repository.ComplainLogRepo;
import com.example.hostal_management_b.repository.ComplainRepo;
import com.example.hostal_management_b.repository.DeanComplainsRepo;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final JdbcTemplate jdbcTemplate;
    private final ComplainLogRepo complainLogRepo;
    private final DeanComplainsRepo deanComplainsRepo;


    //Local variable to Store current Data.
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String dateCreated = currentDate.format(formatter);



    public String exportDailyReport() throws FileNotFoundException, JRException {
        String reportPath = "D:\\Generated_Reports";

        List<ComplainLog> complains=complainLogRepo.findAll();//Retrieving all the daily complains

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(complains);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Faculty of Technology");

        // Saving the report file to the database
        String sql = "INSERT INTO reports (report_name, path, date) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Daily"+dateCreated+".pdf");
            ps.setString(2,reportPath+".pdf");
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\Daily_Complains"+System.currentTimeMillis()+".pdf");


        return "Report generated Successfully at : "+reportPath;
    }





    //Export Monthly report

    public String exportMonthlyReport() throws FileNotFoundException, JRException {
        String reportPath = "D:\\Monthly_report";

        List<DeanComplains> complains=deanComplainsRepo.findAll();//Retrieving all the Monthly complains

        //Loading the .jrxml file and Compiling it
        File file= ResourceUtils.getFile("classpath:monthly_report.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        //Mapping List Data into the Report
        JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(complains);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Created by","Faculty of Technology");

        // Saving the report file to the database
        String sql = "INSERT INTO reports (report_name, path, date) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Daily"+dateCreated+".pdf");
            ps.setString(2,reportPath+".pdf");
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // set the current date and time
            return ps;
        }, keyHolder);

        //Printing the Report
        JasperPrint print= JasperFillManager.fillReport(jasperReport,parameters,source);
        JasperExportManager.exportReportToPdfFile(print,reportPath+"\\Monthly_Complains"+System.currentTimeMillis()+".pdf");


        return "Report generated Successfully at : "+reportPath;
    }



}
