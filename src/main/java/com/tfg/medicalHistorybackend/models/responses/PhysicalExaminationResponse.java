package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalExaminationResponse {

    @Schema(name = "id", example = "1", description = "identify of the physical examination on the database")
    private long id;

    @Schema(name = "size", example = "180", description = "height of the patient")
    private Long size;

    @Schema(name = "weight", example = "80", description = "weight of the patient")
    private Long weight;

    @Schema(name = "bmi", example = "24", description = "body mass index of the patient")
    private Long bmi;

    @Schema(name = "observations", example = "Observations", description = "observations of the physical examination")
    private String observations;

}
