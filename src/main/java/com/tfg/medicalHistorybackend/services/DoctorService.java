package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.jpa.DoctorJPA;
import com.tfg.medicalHistorybackend.models.jpa.UserJPA;
import com.tfg.medicalHistorybackend.models.responses.DoctorResponse;
import com.tfg.medicalHistorybackend.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class DoctorService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final DoctorRepository doctorRepository;
    private final UserService userService;

    public DoctorService(DoctorRepository doctorRepository, UserService userService){
        this.doctorRepository = doctorRepository;
        this.userService = userService;
    }

    public DoctorResponse createDoctor(DoctorResponse doctorResponse){
        LOGGER.info("creating doctor...");

        try {

            UserJPA userJPA = new UserJPA();
            userJPA.setName(doctorResponse.getName());
            userJPA.setSurname(doctorResponse.getSurname());
            userJPA.setDocument(doctorResponse.getDocument());
            userJPA.setEmail(doctorResponse.getEmail());
            userJPA.setRole(doctorResponse.getRole());
            UserJPA newUserJPA = userService.createUserJPA(userJPA);

            DoctorJPA doctorJPA = new DoctorJPA();
            doctorJPA.setMp(doctorResponse.getMp());
            doctorJPA.setInstitution(doctorResponse.getInstitution());
            doctorJPA.setUser(newUserJPA);

            DoctorJPA newDoctorJPA = doctorRepository.save(doctorJPA);
            LOGGER.info("Doctor: {}, created successfully", newDoctorJPA);
            return createDoctorResponse(newDoctorJPA);
        } catch (Exception e) {
            LOGGER.error("Error creating doctor: " + e.getMessage());
            throw e;
        }
    }
    private DoctorResponse createDoctorResponse(DoctorJPA doctorJPA){
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setMp(doctorJPA.getMp());
        doctorResponse.setInstitution(doctorJPA.getInstitution());
        doctorResponse.setName(doctorJPA.getUser().getName());
        doctorResponse.setSurname(doctorJPA.getUser().getSurname());
        doctorResponse.setDocument(doctorJPA.getUser().getDocument());
        doctorResponse.setEmail(doctorJPA.getUser().getEmail());
        doctorResponse.setCreationDate(doctorJPA.getUser().getCreationDate());
        doctorResponse.setRole(doctorJPA.getUser().getRole());
        return doctorResponse;
    }
}
