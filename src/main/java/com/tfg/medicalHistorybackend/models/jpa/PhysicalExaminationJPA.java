package com.tfg.medicalHistorybackend.models.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "physicalExamination")
public class PhysicalExaminationJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "size")
    private Long size;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "bmi")
    private Long bmi;

    @Column(name = "observations")
    private String observations;

    @OneToOne
    @JoinColumn(name = "cus_id")
    private CUSJPA cus;


}
