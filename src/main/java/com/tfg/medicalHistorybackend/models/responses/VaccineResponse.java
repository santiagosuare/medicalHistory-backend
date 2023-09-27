package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineResponse {

    @Schema(name = "id", example = "1", description = "identify of the vaccine on the database")
    private long id;

    @Schema(name = "card", example = "true", description = "if the vaccine has card")
    private boolean card;

    @Schema(name = "complete", example = "true", description = "if the vaccine is complete")
    private boolean complete;

    @Schema(name = "observations", example = "Observations", description = "observations of the vaccine")
    private String observations;
}
