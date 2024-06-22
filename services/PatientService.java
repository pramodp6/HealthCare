package com.health.pramod.HealthCare.services;

import com.health.pramod.HealthCare.dto.PaitientDto;
import com.health.pramod.HealthCare.entity.OtpVerification;
import com.health.pramod.HealthCare.entity.Patients;
import com.health.pramod.HealthCare.exception.EmailSendingException;
import com.health.pramod.HealthCare.repository.OtpRespository;
import com.health.pramod.HealthCare.repository.PatientsRepository;
import com.health.pramod.HealthCare.utility.EmailUtility;
import com.health.pramod.HealthCare.utility.OtpStatus;
import com.health.pramod.HealthCare.utility.OtpUtility;
import com.health.pramod.HealthCare.utility.Role;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Optional;

@Slf4j
@Service
public class PatientService implements UserDetailsService {
    private OtpVerification otpVrification;
    private PatientsRepository patientsRepository;
    private ModelMapper modelMapper;
    private OtpRespository otpRespository;
    private OtpUtility otpUtility;
    private EmailUtility emailUtility;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public PatientService(OtpVerification otpVrification, PatientsRepository patientsRepository, ModelMapper modelMapper, OtpRespository otpRespository, OtpUtility otpUtility, EmailUtility emailUtility, PasswordEncoder passwordEncoder) {
        this.otpVrification = otpVrification;
        this.patientsRepository = patientsRepository;
        this.modelMapper = modelMapper;
        this.otpRespository = otpRespository;
        this.otpUtility = otpUtility;
        this.emailUtility = emailUtility;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createPatient(PaitientDto paitientCreateDto) {

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
        patients.setRole(Role.PATIENT);
        patients.setEnabled(false);


        patientsRepository.save(patients);
        String otp = otpUtility.optverification(6);
        otpVrification.setPatientsId(patients);
        otpVrification.setVerificationCode(otp);
        otpVrification.setCreateAt(LocalDateTime.now());
        otpVrification.setVerificationExpireCode(LocalDateTime.now().plusMinutes(2));
        otpVrification.setStatus(OtpStatus.SENT);

        otpRespository.save(otpVrification);
        try{
            emailUtility.sendEmail(paitientCreateDto.getEmail(), otp);
        }catch (Exception e){
            throw new EmailSendingException("Failed to send OTP email due to internet issues");

        }
        return true;
    }

    public boolean verifyPatient(String email, String otpCode) {
        Patients patients = patientsRepository.findByEmail(email);
        log.debug(patients.toString()+"Noida");
        if (patients == null) {
            return false;
        }
        System.out.println(otpCode+"otpCode");

        OtpVerification otpEntry = otpRespository.findByPatientsIdAndVerificationCode(patients, otpCode);


        if (otpEntry == null) {
            return  false;

        }

        if(otpEntry.getVerificationExpireCode().isBefore(LocalDateTime.now())){
            return false;
        }
            otpEntry.setVerificationCode(null);
            otpEntry.setStatus(OtpStatus.GENERATED);
            otpEntry.setVerificationExpireCode(null);

            otpEntry.setVerificationTime(LocalDateTime.now());
            otpRespository.save(otpEntry);
            patients.setEnabled(true);
            patientsRepository.save(patients);

            return true;
        }





    public boolean resendMail(String email) {
        Patients patients = patientsRepository.findByEmail(email);
        if(patients==null){
            return false;
        }

        System.out.print(patients);

        OtpVerification otpEntry = otpRespository.findByPatientsId(patients);
        System.out.print(otpEntry);
        if(otpEntry==null){
            return false;
        }


        String otp = otpUtility.optverification(6);
      ;

        if(otpEntry.getVerificationExpireCode().isBefore(LocalDateTime.now())){
            return false;
        }

        otpEntry.setPatientsId(patients);
        otpEntry.setVerificationCode(otp);
        otpEntry.setCreateAt(LocalDateTime.now());
        otpEntry.setVerificationExpireCode(LocalDateTime.now().plusMinutes(2));
        otpEntry.setStatus(OtpStatus.SENT);
        otpRespository.save(otpEntry);
        try{
            emailUtility.sendEmail(email, otp);
        }catch (Exception e){
            throw new EmailSendingException("Failed to send OTP email due to internet issues");

        }
        return true;

    }


    public PaitientDto UpdateDetailsPatient(Long id, PaitientDto paitientDto) {
        Optional<Patients> patients = patientsRepository.findById(id);

        if (patients.isPresent()) {
            Patients patient = patients.get();
            paitientDto.setUsername(patient.getUsername());
            paitientDto.setFirstname(patient.getFirstname());
            paitientDto.setUsername(patient.getUsername());

            paitientDto.setEmail(patient.getEmail());
            paitientDto.setPatientId(patient.getPatientId());
            paitientDto.setAddress(paitientDto.getAddress());

            patient.setUpdateAt(LocalDateTime.now());
            patientsRepository.save(patient);
            return paitientDto;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patients patients = patientsRepository.findByEmail(username);
        return patients;
    }

    public boolean forgotAccount(String email) {

        String otp = otpUtility.optverification(6);
        Patients patients = patientsRepository.findByEmail(email);

        if(patients==null){
            return false;
        }
        System.out.print(otp);


        OtpVerification otpEntry = otpRespository.findByPatientsId(patients);
        System.out.print(otpEntry);
        if(otpEntry==null){
           return false;
        }

        otpEntry.setOtpForget(otp);
        otpEntry.setOtpForetExpire(LocalDateTime.now().plusMinutes(2));
        otpRespository.save(otpEntry);

        try{
            emailUtility.sendEmail(email, otp);
        }catch (Exception e){
            throw new EmailSendingException("Failed to send OTP email due to internet issues");

        }
        return true;


    }

    public boolean forgetOtpresend(String email) {
        String otp = otpUtility.optverification(6);
        Patients patients = patientsRepository.findByEmail(email);

        if(patients==null){
            return false;
        }



        OtpVerification otpEntry = otpRespository.findByPatientsId(patients);
        if(otpEntry==null){
            return false;
        }
        log.debug("Generated OTP: " + otp);
        otpEntry.setOtpForget(otp);
        otpEntry.setOtpForetExpire(LocalDateTime.now().plusMinutes(2));
        otpRespository.save(otpEntry);
        try{
            emailUtility.sendEmail(email, otp);
        }catch (Exception e){
            throw new EmailSendingException("Failed to send OTP email due to internet issues");

        }
        return true;

    }


    public boolean verifyforgotOtp(String email, String otpCode, String newPassword) {

        Patients patients = patientsRepository.findByEmail(email);
       if(patients==null) {
            return false;
        }

        String currentpassword = patients.getPassword();
        if(passwordEncoder.matches(newPassword,currentpassword)){
            return false;
        }




        OtpVerification otpVerify = otpRespository.findByPatientsIdAndOtpForget(patients,otpCode);
        if(otpVerify==null){
            return false;
        }

        if(otpVerify.getOtpForetExpire().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Otp expired.");

        }
        otpVerify.setOtpForget(null);
        otpVerify.setOtpForetExpire(null);
        otpVerify.setOtpForgetVerification(LocalDateTime.now());
        patients.setPassword(passwordEncoder.encode(newPassword));
        otpRespository.save(otpVerify);

        return true;
    }


}






