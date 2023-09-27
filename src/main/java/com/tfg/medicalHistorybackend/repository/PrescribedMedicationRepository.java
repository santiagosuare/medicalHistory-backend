package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.PrescribedMedicationJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescribedMedicationRepository extends JpaRepository<PrescribedMedicationJPA, Long> {

    @Query("SELECT p FROM PrescribedMedicationJPA p WHERE p.cus.id = ?1")
    public Optional<PrescribedMedicationJPA> findByCUSId(String id);

}
