package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CUSResponse {

    @Schema(name = "id", example = "1", description = "identify of the patient on the database")
    private Long id;

    @Schema(name = "birthDate", example = "2021-05-01T12:00:00.000+00:00", description = "birth date of the patient")
    private ZonedDateTime birthDate;

    @Schema(name = "gender")
    private char gender;

    @Schema(name = "address", example = "Av. Siempreviva 742", description = "address of the patient")
    private String address;

    @Schema(name = "city", example = "Springfield", description = "city of the patient")
    private String city;

    @Schema(name = "prescribedMedication")
    private PrescribedMedicationResponse prescribedMedication;

    @Schema(name = "vaccine")
    private VaccineResponse vaccine;

    @Schema(name = "physicalExamination")
    private PhysicalExaminationResponse physicalExamination;

    @Schema(name = "pathologicalHistory")
    private PathologicalHistoryResponse pathologicalHistory;

}
