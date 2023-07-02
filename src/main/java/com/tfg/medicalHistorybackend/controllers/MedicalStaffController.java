package com.tfg.medicalHistorybackend.controllers;

import com.tfg.medicalHistorybackend.models.responses.MedicalStaffResponse;
import com.tfg.medicalHistorybackend.services.MedicalStaffService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/medicalStaff")
public class MedicalStaffController {

    private final MedicalStaffService medicalStaffService;

    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @PostMapping("/createMedicalStaff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff created"),
            @ApiResponse(responseCode = "400", description = "MedicalStaff not created")
    })
    @Tag(name = "MedicalStaffController", description = "MedicalStaffController API")
    public ResponseEntity<MedicalStaffResponse> createMedicalStaff(@RequestBody MedicalStaffResponse medicalStaffResponse){
        try{
            return ResponseEntity.ok(medicalStaffService.createMedicalStaff(medicalStaffResponse));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getMedicalStaff/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff found"),
            @ApiResponse(responseCode = "400", description = "MedicalStaff not found")
    })
    @Tag(name = "MedicalStaffController", description = "MedicalStaffController API")
    public ResponseEntity<MedicalStaffResponse> getMedicalStaff(@PathVariable String id){
        try{
            return ResponseEntity.ok(medicalStaffService.getMedicalStaffById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllMedicalStaff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaffs found"),
            @ApiResponse(responseCode = "400", description = "MedicalStaffs not found")
    })
    @Tag(name = "MedicalStaffController", description = "MedicalStaffController API")
    public ResponseEntity<List<MedicalStaffResponse>> getAllMedicalStaff(){
        try{
            return ResponseEntity.ok(medicalStaffService.getAllMedicalStaff());
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deleteMedicalStaff/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalStaff deleted"),
            @ApiResponse(responseCode = "400", description = "MedicalStaff not deleted")
    })
    @Tag(name = "MedicalStaffController", description = "MedicalStaffController API")
    public ResponseEntity<String> deleteMedicalStaff(@PathVariable String id){
        try{
            medicalStaffService.deleteMedicalStaff(id);
            return ResponseEntity.ok("MedicalStaff deleted");
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
