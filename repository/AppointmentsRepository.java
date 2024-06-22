package com.health.pramod.HealthCare.repository;

import com.health.pramod.HealthCare.entity.Appointments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointments,Long> {

}
