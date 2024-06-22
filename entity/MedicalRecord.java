package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medicalRecord")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int recordId;
    @ManyToOne()
    @JoinColumn(name = "patient_Id", nullable = false)
    private Patients patients;

    @ManyToOne
    @JoinColumn(name ="doctor_id",nullable = false)
    private Doctors doctors;
    private String recordDate;
    private String diagnosis;
    private String treatment;
}
