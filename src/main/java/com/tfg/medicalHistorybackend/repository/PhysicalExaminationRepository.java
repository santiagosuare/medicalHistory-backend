package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.PhysicalExaminationJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalExaminationRepository extends JpaRepository<PhysicalExaminationJPA, String> {
}
