package com.example.hostal_management_b.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "property")
public class Property {

    @Id
    @Column(unique = true)
    private String propID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String status;
    @Column(nullable = false)
    private int room_no;

}
