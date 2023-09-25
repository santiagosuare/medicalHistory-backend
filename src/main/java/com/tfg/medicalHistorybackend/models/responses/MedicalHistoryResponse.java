package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryResponse {
    @Schema(name = "id", example = "1", description = "identify of the medical history on the database")
    private long id;

    @Schema(name = "creationDate", example = "2021-05-01T12:00:00.000+00:00", description = "date of creation of the medical history")
    private ZonedDateTime creationDate;

    @Schema(name = "diagnostic", example = "COVID-19", description = "diagnostic of the medical history")
    private String diagnostic;

    @Schema(name = "studyRequest", example = "Radiography", description = "study request of the medical history")
    private String studyRequest;

    @Schema(name = "orderPrescription", example = "Paracetamol", description = "order prescription of the medical history")
    private String orderPrescription;

    @Schema(name = "patientId", description = "patient of the medical history")
    private String patientId;

    @Schema(name = "doctor", description = "doctor of the medical history")
    private DoctorResponse doctor;

    @Schema(name = "medicalStaff", description = "medical staff of the medical history")
    private MedicalStaffResponse medicalStaff;
}
