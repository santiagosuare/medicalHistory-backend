package com.tfg.medicalHistorybackend.controllers;

import com.tfg.medicalHistorybackend.models.responses.DoctorResponse;
import com.tfg.medicalHistorybackend.services.DoctorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/createDoctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor created"),
            @ApiResponse(responseCode = "400", description = "Doctor not created")
    })
    @Tag(name = "DoctorController", description = "DoctorController API")
    public ResponseEntity<DoctorResponse> getDoctor(@RequestBody DoctorResponse doctorResponse){
        try {
            return ResponseEntity.ok(doctorService.createDoctor(doctorResponse));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
