package com.tfg.medicalHistorybackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO extends UserDTO{
    private long id;
    private int mp;
    private String institution;
}
