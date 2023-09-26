package com.tfg.medicalHistorybackend.controllers;

import com.tfg.medicalHistorybackend.models.responses.PatientResponse;
import com.tfg.medicalHistorybackend.models.responses.UserResponse;
import com.tfg.medicalHistorybackend.services.PatientService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/createPatient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient created"),
            @ApiResponse(responseCode = "400", description = "Patient not created")
    })
    @Tag(name = "PatientController", description = "PatientController API")
    public ResponseEntity<UserResponse> createPatient(@RequestBody UserResponse userResponse){
        try{
            return ResponseEntity.ok(patientService.createPatient(userResponse));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



}
