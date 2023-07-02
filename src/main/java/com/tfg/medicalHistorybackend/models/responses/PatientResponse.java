package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse extends UserResponse{

    @Schema(name = "id", example = "1", description = "identify of the patient on the database")
    private long id;

    @Schema(name = "age", example = "20", description = "age of the patient")
    private int age;

    @Schema(name = "cellphone", example = "123456789", description = "cellphone of the patient")
    private Long cellphone;

    @Schema(name = "healthInsurance", example = "OSDE", description = "health insurance of the patient")
    private String healthInsurance;

    @Schema(name = "member", example = "123456", description = "member of the health insurance of the patient")
    private int member;
}
