package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.CUSJPA;
import com.tfg.medicalHistorybackend.models.jpa.PatientJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CUSRepository extends JpaRepository<CUSJPA, String> {
    @Query("SELECT c FROM CUSJPA c WHERE c.patient.id = ?1")
    public Optional<CUSJPA> findByPatientId(String id);
}
