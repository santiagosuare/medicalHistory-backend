package com.tfg.medicalHistorybackend.models.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine")
public class VaccineJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "card")
    private boolean card;

    @Column(name = "complete")
    private boolean complete;

    @Column(name = "observations")
    private String observations;

    @OneToOne
    @JoinColumn(name = "cus_id")
    private CUSJPA cus;

}
