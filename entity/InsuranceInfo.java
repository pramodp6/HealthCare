package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "insuranceInfo")
public class InsuranceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "information_id")
    private Long informationId;
    private String policyNumber;
    private Integer insuranceCompanyId;
    private Integer policyHolderId;
    private Date startDate;
    private Date endDate;
    private  Double premiumAmount;
    @ManyToOne()
    @JoinColumn(name = "patient_id",nullable = false)
    private Patients patients;
    //@Lob
    //@Column(name="",columnDefinition = "text")
    private String coverageDetails;
}
