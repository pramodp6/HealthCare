package com.health.pramod.HealthCare.repository;

import com.health.pramod.HealthCare.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {
    Patients findByEmail(String email);



}
