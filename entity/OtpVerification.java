package com.health.pramod.HealthCare.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.health.pramod.HealthCare.utility.OtpStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "otp")
public class OtpVerification {

    private static OtpVerification instance;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "otp_id")
    private Long otpId;
    @ManyToOne()
    @JoinColumn(name = "paitent_id",nullable = false)
    private Patients patientsId;


    @Column(name = "verification_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String verificationCode;

    @Column(name = "verification_expire_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime verificationExpireCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OtpStatus status;

    @Column(name = "create_at")
    private LocalDateTime createAt;


    @Column(name = "otp_forget_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String otpForget;

    @Column(name = "otp_forget_expire")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime otpForetExpire;

    @Column(name = "otp_forget_verification")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime otpForgetVerification;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "otp_resend_count")
    private Integer otpResendCount;


    @Column(name = "last_Resend_time")
    private LocalDateTime lastResendTime;
    @Column(name = "verification_time")
    private LocalDateTime verificationTime;


    public OtpVerification() {
    }

    public static OtpVerification getInstance(){
        if(instance == null){
            return new OtpVerification();
        }
        return instance;
    }
}
