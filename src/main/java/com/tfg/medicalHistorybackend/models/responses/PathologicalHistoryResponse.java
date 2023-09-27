package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathologicalHistoryResponse {

    @Schema(name = "id", example = "1", description = "identify of the pathological history on the database")
    private long id;

    @Schema(name = "surgeries", example = "Surgeries", description = "surgeries of the patient")
    private String surgeries;

    @Schema(name = "cardiovascular", example = "cardiovascular", description = "cardiovascular of the patient")
    private String cardiovascular;

    @Schema(name = "allergies", example = "allergies", description = "allergies of the patient")
    private String allergies;

    @Schema(name = "oftalmologicos", example = "oftalmologicos", description = "oftalmologicos of the patient")
    private String oftalmologicos;

    @Schema(name = "others", example = "others", description = "others pathological history of the patient")
    private String others;
}
