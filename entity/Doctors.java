package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctors")
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String contactNumbe;
}
