package com.health.pramod.HealthCare.services;


import com.health.pramod.HealthCare.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }
}
