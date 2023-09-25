package com.tfg.medicalHistorybackend.controllers;

import com.tfg.medicalHistorybackend.models.responses.MedicalHistoryResponse;
import com.tfg.medicalHistorybackend.services.MedicalHistoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/medicalHistory")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @CrossOrigin
    @PostMapping("/createMedicalHistory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalHistory created"),
            @ApiResponse(responseCode = "400", description = "MedicalHistory not created")
    })
    @Tag(name = "MedicalHistoryController", description = "MedicalHistoryController API")
    public ResponseEntity<MedicalHistoryResponse> createMedicalHistory(@RequestBody MedicalHistoryResponse medicalHistoryResponse) {
        try {
            return ResponseEntity.ok(medicalHistoryService.createMedicalHistory(medicalHistoryResponse));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/getMedicalHistory/{patientId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalHistory found"),
            @ApiResponse(responseCode = "400", description = "MedicalHistory not found")
    })
    @Tag(name = "MedicalHistoryController", description = "MedicalHistoryController API")
    public ResponseEntity<List<MedicalHistoryResponse>> getMedicalHistory(@PathVariable String patientId) {
        try {
            return ResponseEntity.ok(medicalHistoryService.getMedicalHistoryByPatientId(patientId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
