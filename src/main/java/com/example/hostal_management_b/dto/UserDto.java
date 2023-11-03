package com.example.hostal_management_b.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {   //For getting firstname and Lastname for Viewing purposes of Angular Frontend
    private String firstname;
    private String lastname;
    private String reg_no;
}
