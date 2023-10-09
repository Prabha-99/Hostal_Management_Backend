package com.example.hostal_management_b.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Academic_Registration_Request {
    private int id;
    private String firstname;
    private String lastname;
    private String staff_id;
    private String email;
    private String password;
    private String role;
}
