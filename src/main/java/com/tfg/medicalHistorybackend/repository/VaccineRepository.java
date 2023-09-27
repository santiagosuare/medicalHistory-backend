package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.VaccineJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineJPA, String> {

    @Query("SELECT v FROM VaccineJPA v WHERE v.cus.id = ?1")
    public Optional<VaccineJPA> findByCUSId(String id);
}
