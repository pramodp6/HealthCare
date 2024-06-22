package com.health.pramod.HealthCare.repository;

import com.health.pramod.HealthCare.entity.InsuranceInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceInfoRepository extends JpaRepository<InsuranceInfo,Long> {
}
