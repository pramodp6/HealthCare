package com.health.pramod.HealthCare.repository;

import com.health.pramod.HealthCare.entity.HealthcareProfessionals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthcareProfessionalsRepository extends JpaRepository<HealthcareProfessionals, Long> {
}
