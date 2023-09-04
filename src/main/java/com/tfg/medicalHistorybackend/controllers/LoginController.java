package com.tfg.medicalHistorybackend.controllers;

import com.tfg.medicalHistorybackend.models.responses.DoctorResponse;
import com.tfg.medicalHistorybackend.models.responses.LoginResponse;
import com.tfg.medicalHistorybackend.models.responses.UserResponse;
import com.tfg.medicalHistorybackend.services.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/loginUser/{document}/{password}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged"),
            @ApiResponse(responseCode = "400", description = "User not logged")
    })
    @Tag(name = "LoginController", description = "LoginController API")
    public ResponseEntity<LoginResponse> loginUser(@PathVariable String document, @PathVariable String password){
        return ResponseEntity.ok(userService.loginUser(document, password));
    }

}
