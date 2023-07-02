package com.tfg.medicalHistorybackend.models.helpers;

public enum Roles {
    PATIENT(1, "patient"),
    DOCTOR(2,"doctor"),
    MEDICAL_STAFF(3,"medicalStaff");

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Roles(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
