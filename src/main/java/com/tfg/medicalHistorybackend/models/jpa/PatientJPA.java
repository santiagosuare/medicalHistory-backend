package com.tfg.medicalHistorybackend.models.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class PatientJPA{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "age")
    private int age;

    @Column(name = "cellphone")
    private Long cellphone;

    @Column(name = "healthInsurance")
    private String healthInsurance;

    @Column(name = "member")
    private int member;

    @Column(name = "status")
    private boolean status;

    @OneToOne
    private UserJPA user;
}
