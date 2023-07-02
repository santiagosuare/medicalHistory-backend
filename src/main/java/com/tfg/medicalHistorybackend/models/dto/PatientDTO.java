package com.tfg.medicalHistorybackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO{
    private long id;
    private int age;
    private Long cellphone;
    private String healthInsurance;
    private int member;
}
