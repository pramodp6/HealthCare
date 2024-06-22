package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "appointments")
public class Appointments{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_Id")
    private Long  appointmentId;
    @ManyToOne()
    @JoinColumn(name = "patient_id",nullable = false)
    private Patients patients;
    @ManyToOne()
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctors doctors;
    private Date appointmentDate;
    private String reason;
    private String status;
}
