package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.PhysicalExaminationJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysicalExaminationRepository extends JpaRepository<PhysicalExaminationJPA, String> {

    @Query("SELECT p FROM PhysicalExaminationJPA p WHERE p.cus.id = ?1")
    public Optional<PhysicalExaminationJPA> findByCUSId(String id);
}
