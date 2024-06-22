package com.health.pramod.HealthCare.services;

import com.health.pramod.HealthCare.dto.PaitientDto;
import com.health.pramod.HealthCare.entity.OtpVerification;
import com.health.pramod.HealthCare.entity.Patients;
import com.health.pramod.HealthCare.repository.OtpRespository;
import com.health.pramod.HealthCare.repository.PatientsRepository;
import com.health.pramod.HealthCare.utility.OtpStatus;
import com.health.pramod.HealthCare.utility.OtpUtility;
import com.health.pramod.HealthCare.utility.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private PatientsRepository patientsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OtpVerification otpVrification;
    @Autowired
    private OtpUtility otpUtility;
    @Autowired
    private OtpRespository otpRespository;

    @Autowired
    public AdminService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }


    public List<PaitientDto> allPatientData() {

        return patientsRepository.findAll().stream().map(patients -> {
                    PaitientDto paitientDto = new PaitientDto();
                    paitientDto.setPatientId(patients.getPatientId());
                    paitientDto.setUsername(patients.getUsername());
                    paitientDto.setFirstname(patients.getFirstname());
                    paitientDto.setLastname(patients.getLastname());
                    paitientDto.setGender(patients.getGender());
                    paitientDto.setDob(patients.getDob());
                    paitientDto.setEmail(patients.getEmail());
                    paitientDto.setContact(patients.getContact());
                    paitientDto.setAddress(patients.getAddress());
                    return paitientDto;
                })
                .collect(Collectors.toList());


    }


    public boolean createDoctor(PaitientDto paitientCreateDto) {

        if (patientsRepository.findByEmail(paitientCreateDto.getEmail()) != null) {
            return false;
        }
        Patients patients = new Patients();
        patients.setUsername(paitientCreateDto.getUsername());
        patients.setFirstname(paitientCreateDto.getFirstname());
        patients.setLastname(paitientCreateDto.getLastname());
        patients.setGender(paitientCreateDto.getGender());
        patients.setEmail(paitientCreateDto.getEmail());
        patients.setPassword(passwordEncoder.encode(paitientCreateDto.getPassword()));
        patients.setCredentialsExpirationDate(LocalDate.now().plusDays(90)); // Assuming 90 days validity


        patients.setContact(paitientCreateDto.getContact());
        patients.setAddress(paitientCreateDto.getAddress());
        patients.setDob(paitientCreateDto.getDob());
        patients.setCreateAt(LocalDateTime.now());
        patients.setRole(Role.DOCTOR);
        patients.setEnabled(false);

        patientsRepository.save(patients);
        String otp = otpUtility.optverification(6);
        otpVrification.setPatientsId(patients);
        otpVrification.setVerificationCode(otp);
        otpVrification.setCreateAt(LocalDateTime.now());
        otpVrification.setVerificationExpireCode(LocalDateTime.now().plusMinutes(2));
        otpVrification.setStatus(OtpStatus.SENT);

        otpRespository.save(otpVrification);
        // emailUtility.sendEmail(paitientCreateDto.getEmail(), otp);
        return true;
    }

    public boolean verifyPatient(String email, String code) {
        Patients patients = patientsRepository.findByEmail(email);
        if (patients == null) {
            return false;
        }
        return false;
    }
}
