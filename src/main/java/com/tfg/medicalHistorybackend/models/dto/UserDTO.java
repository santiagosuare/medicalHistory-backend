package com.tfg.medicalHistorybackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private int document;
    private String email;
    private ZonedDateTime creationDate;
    private int role;
}
