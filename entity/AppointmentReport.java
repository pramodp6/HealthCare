package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "appointmentReport")
public class AppointmentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
   private Long  reportId;
    @Column(name = "report_type")
   private String reporType;
   private Date generated_at;
   private Integer total_appointments;
}
