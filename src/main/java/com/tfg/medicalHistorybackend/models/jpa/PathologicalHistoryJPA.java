package com.tfg.medicalHistorybackend.models.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pathologicalHistory")
public class PathologicalHistoryJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "surgeries")
    private String surgeries;

    @Column(name = "cardiovascular")
    private String cardiovascular;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "oftalmologicos")
    private String oftalmologicos;

    @Column(name = "others")
    private String others;

    @OneToOne
    @JoinColumn(name = "cus_id")
    private CUSJPA cus;

}
