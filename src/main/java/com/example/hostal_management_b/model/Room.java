package com.example.hostal_management_b.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "rooms")
public class Room {
    @Id
    private String roomID;
    private String hostalType;
    private int room_no;
    @Column(nullable = true)
    private int no_of_items;
    @Column(nullable = true)
    private int no_of_students;



}
