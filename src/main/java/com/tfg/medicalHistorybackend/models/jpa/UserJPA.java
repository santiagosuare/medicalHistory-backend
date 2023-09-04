package com.tfg.medicalHistorybackend.models.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "document")
    private int document;

    @Column(name = "email")
    private String email;

    @Column(name = "creationDate")
    private ZonedDateTime creationDate;

    @Column(name = "role")
    private int role;

    @Column(name = "password")
    private String password;
}
