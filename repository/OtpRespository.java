package com.health.pramod.HealthCare.repository;


import com.health.pramod.HealthCare.entity.OtpVerification;
import com.health.pramod.HealthCare.entity.Patients;
import com.health.pramod.HealthCare.utility.OtpStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRespository extends JpaRepository<OtpVerification,Long> {

   OtpVerification findByPatientsIdAndVerificationCode(Patients patientsId, String verificationCode);
   OtpVerification findByPatientsId(Patients patientsId );
   OtpVerification findByPatientsIdAndOtpForget(Patients patients,String otpCode);
  // OtpVerification findByPatientsId(Long patientsId);



}
