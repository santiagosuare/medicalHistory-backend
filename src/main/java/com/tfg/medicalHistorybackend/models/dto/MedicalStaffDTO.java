package com.tfg.medicalHistorybackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffDTO{
    private long id;
    private int mp;
    private String institution;
}
