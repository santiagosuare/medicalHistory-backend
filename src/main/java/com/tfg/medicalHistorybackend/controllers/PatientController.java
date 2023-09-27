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

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("/getPatientByDocument/{document}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "400", description = "Patient not found")
    })
    @Tag(name = "PatientController", description = "PatientController API")
    public ResponseEntity<PatientResponse> getPatientByDocument(@PathVariable String document){
        try{
            return ResponseEntity.ok(patientService.getPatientByDocument(document));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/getPatientById/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "400", description = "Patient not found")
    })
    @Tag(name = "PatientController", description = "PatientController API")
    public ResponseEntity<UserResponse> getPatientById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(patientService.getPatientById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



}
