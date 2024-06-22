package com.health.pramod.HealthCare.repository;

import com.health.pramod.HealthCare.entity.Doctors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors,Long> {
}
