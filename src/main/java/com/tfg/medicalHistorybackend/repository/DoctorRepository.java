package com.tfg.medicalHistorybackend.repository;

import com.tfg.medicalHistorybackend.models.jpa.DoctorJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorJPA, String> {

    @Query("SELECT d FROM DoctorJPA d JOIN d.user u ON u.id = d.user.id WHERE d.user.id = ?1")
    public Optional<DoctorJPA> findByUserId(String userId);
}
