package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescribedMedicationResponse {

    @Schema(name = "id", example = "1", description = "identify of the prescribed medication on the database")
    private long id;

    @Schema(name = "medication", example = "Paracetamol", description = "medication prescribed to the patient")
    private String medication;

}
