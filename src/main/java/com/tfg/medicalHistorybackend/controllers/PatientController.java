package com.tfg.medicalHistorybackend.controllers;

import com.tfg.medicalHistorybackend.models.responses.PatientResponse;
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
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientResponse patientResponse){
        try{
            return ResponseEntity.ok(patientService.createPatient(patientResponse));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/getPatient/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "400", description = "Patient not found")
    })
    @Tag(name = "PatientController", description = "PatientController API")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable String id){
        try{
            return ResponseEntity.ok(patientService.getPatientById(id));
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
    @GetMapping("/getAllPatients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patients found"),
            @ApiResponse(responseCode = "400", description = "Patients not found")
    })
    @Tag(name = "PatientController", description = "PatientController API")
    public ResponseEntity<List<PatientResponse>> getAllPatients(){
        try{
            return ResponseEntity.ok(patientService.getAllPatients());
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deletePatient/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient deleted"),
            @ApiResponse(responseCode = "400", description = "Patient not deleted")
    })
    @Tag(name = "PatientController", description = "PatientController API")
    public ResponseEntity<String> deletePatient(@PathVariable String id){
        try{
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient deleted");
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
