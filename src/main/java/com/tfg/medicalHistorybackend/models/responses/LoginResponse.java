package com.tfg.medicalHistorybackend.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @Schema(name = "isLogged", example = "true", description = "if the user is logged")
    private boolean isLogged;

    @Schema(name = "role", example = "1", description = "role of the user")
    private int role;

    @Schema(name = "role", example = "1", description = "role of the user")
    private long userId;
}
