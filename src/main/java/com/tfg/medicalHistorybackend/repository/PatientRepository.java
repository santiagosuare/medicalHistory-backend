package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.PatientJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientJPA, String> {
}
