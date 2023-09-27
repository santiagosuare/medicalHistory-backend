package com.tfg.medicalHistorybackend.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(name = "id", example = "1", description = "identify of the user on the database")
    private Long id;

    @Schema(name = "name", example = "Juan", description = "name of the user")
    private String name;

    @Schema(name = "surname", example = "Perez", description = "surname of the user")
    private String surname;

    @Schema(name = "document", example = "12345678", description = "document of the user")
    private int document;

    @Schema(name = "email", example = "juanPerez@gmail.com", description = "email of the user")
    private String email;

    @Schema(name = "creationDate", example = "2021-05-01T12:00:00.000+00:00", description = "date of creation of the user")
    private ZonedDateTime creationDate;

    @Schema(name = "role", example = "1", description = "role of the user")
    private int role;

    private PatientResponse patient;
}
