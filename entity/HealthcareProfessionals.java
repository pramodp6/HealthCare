package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="healthcareProfessionals")
public class HealthcareProfessionals {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "healthcare_professional_Id")
   private Long healthcareProfessionalId;
    private String name;
    private String specialty;
    private String contact_info;
    @Lob
 @Column(name = "bio",columnDefinition = "TEXT")
    private String bio;

    private String availability;
}
