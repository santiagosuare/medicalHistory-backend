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
@Table(name = "medicalHistory")
public class MedicalHistoryJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "creationDate")
    private ZonedDateTime creationDate;

    @Column(name = "diagnostic")
    private String diagnostic;

    @Column(name = "studyRequest")
    private String studyRequest;

    @Column(name = "orderPrescription")
    private String orderPrescription;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientJPA patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorJPA doctor;

    @ManyToOne
    @JoinColumn(name = "medicalStaff_id")
    private MedicalStaffJPA medicalStaff;

}
