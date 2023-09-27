package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.PathologicalHistoryJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathologicalHistoryRepository extends JpaRepository<PathologicalHistoryJPA, String> {
}
