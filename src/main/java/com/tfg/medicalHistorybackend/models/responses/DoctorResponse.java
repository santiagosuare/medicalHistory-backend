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
public class DoctorResponse extends UserResponse{

    @Schema(name = "id", example = "1", description = "identify of the doctor on the database")
    private Long id;

    @Schema(name = "mp", example = "123456", description = "medical practitioner number of the doctor")
    private int mp;

    @Schema(name = "institution", example = "Hospital Private Allende", description = "institution of the doctor")
    private String institution;
}
