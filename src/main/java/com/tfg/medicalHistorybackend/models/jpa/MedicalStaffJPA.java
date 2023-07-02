package com.tfg.medicalHistorybackend.models.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicalStaff")
public class MedicalStaffJPA{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "mp")
    private int mp;

    @Column(name = "institution")
    private String institution;

    @Column(name = "status")
    private boolean status;

    @OneToOne
    private UserJPA user;
}
