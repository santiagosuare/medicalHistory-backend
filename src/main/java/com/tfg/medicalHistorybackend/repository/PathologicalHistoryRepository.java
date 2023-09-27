package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.PathologicalHistoryJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PathologicalHistoryRepository extends JpaRepository<PathologicalHistoryJPA, String> {

    @Query("SELECT p FROM PathologicalHistoryJPA p WHERE p.cus.id = ?1")
    public Optional<PathologicalHistoryJPA> findByCUSId(String id);
}
