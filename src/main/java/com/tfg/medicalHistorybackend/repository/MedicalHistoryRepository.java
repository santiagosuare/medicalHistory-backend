package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.MedicalHistoryJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistoryJPA, String> {

    List<MedicalHistoryJPA> findByPatientId(long patient_id);
}
