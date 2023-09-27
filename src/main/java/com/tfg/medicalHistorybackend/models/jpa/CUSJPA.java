package com.tfg.medicalHistorybackend.models.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cus")
public class CUSJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "birthDate")
    private ZonedDateTime birthDate;

    @Column(name = "gender")
    private char gender;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private PatientJPA patient;
}
