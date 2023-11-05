package com.example.hostal_management_b.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "deancomplains")
public class DeanComplains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cID;
    @JsonProperty("cType")
    private String cType;
    private String propID;
    private long room;
    private String description;
    private String imagePath;
    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
