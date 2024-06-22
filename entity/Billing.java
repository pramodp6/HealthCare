package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_ID")
    private Long billingId;
    @ManyToOne()
    @JoinColumn(name = "patient_Id", nullable = false)


    private Patients patients;
    private Date billingDate;
    private Double  amount;
    private String paymentStatus;
}
