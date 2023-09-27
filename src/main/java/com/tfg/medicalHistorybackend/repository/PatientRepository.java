package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.PatientJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientJPA, String> {

    @Query("SELECT p FROM PatientJPA p WHERE p.user.document = ?1")
    public Optional<PatientJPA> findByUserDocument(String document);


}
