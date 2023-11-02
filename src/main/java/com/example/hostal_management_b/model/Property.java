package com.example.hostal_management_b.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Property")
public class Property {

    @Id

    private String propID;
    private String name;
    private String status;

}
