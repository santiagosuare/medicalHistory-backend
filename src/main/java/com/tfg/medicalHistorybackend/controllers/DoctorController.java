package com.tfg.medicalHistorybackend.controllers;

import com.tfg.medicalHistorybackend.models.responses.DoctorResponse;
import com.tfg.medicalHistorybackend.services.DoctorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("/getDoctor/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor found"),
            @ApiResponse(responseCode = "400", description = "Doctor not found")
    })
    @Tag(name = "DoctorController", description = "DoctorController API")
    public ResponseEntity<DoctorResponse> getDoctor(@PathVariable String id){
        try{
            return ResponseEntity.ok(doctorService.getDoctorById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/getDoctorByUserId/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor found"),
            @ApiResponse(responseCode = "400", description = "Doctor not found")
    })
    @Tag(name = "DoctorController", description = "DoctorController API")
    public ResponseEntity<DoctorResponse> getDoctorByUserId(@PathVariable String userId){
        try{
            return ResponseEntity.ok(doctorService.getDoctorByUserId(userId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/getAllDoctors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctors found"),
            @ApiResponse(responseCode = "400", description = "Doctors not found")
    })
    @Tag(name = "DoctorController", description = "DoctorController API")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors(){
        try{
            return ResponseEntity.ok(doctorService.getAllDoctor());
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deleteDoctor/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor deleted"),
            @ApiResponse(responseCode = "400", description = "Doctor not deleted")
    })
    @Tag(name = "DoctorController", description = "DoctorController API")
    public ResponseEntity<String> deleteDoctor(@PathVariable String id){
        try{
            doctorService.deleteDoctor(id);
            return ResponseEntity.ok("Doctor deleted");
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
