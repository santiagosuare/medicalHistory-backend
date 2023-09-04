package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.helpers.Roles;
import com.tfg.medicalHistorybackend.models.jpa.PatientJPA;
import com.tfg.medicalHistorybackend.models.jpa.UserJPA;
import com.tfg.medicalHistorybackend.models.responses.PatientResponse;
import com.tfg.medicalHistorybackend.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final PatientRepository patientRepository;
    private final UserService userService;

    public PatientService(PatientRepository patientRepository, UserService userService){
        this.patientRepository = patientRepository;
        this.userService = userService;
    }

    public PatientResponse createPatient(PatientResponse patientResponse){
        LOGGER.info("creating patient...");

        try {
            UserJPA userJPA = new UserJPA();
            userJPA.setName(patientResponse.getName());
            userJPA.setSurname(patientResponse.getSurname());
            userJPA.setDocument(patientResponse.getDocument());
            userJPA.setEmail(patientResponse.getEmail());
            userJPA.setRole(Roles.PATIENT.getId());
            UserJPA newUserJPA = userService.createUserJPA(userJPA);

            PatientJPA patientJPA = new PatientJPA();
            patientJPA.setAge(patientResponse.getAge());
            patientJPA.setCellphone(patientResponse.getCellphone());
            patientJPA.setHealthInsurance(patientResponse.getHealthInsurance());
            patientJPA.setMember(patientResponse.getMember());
            patientJPA.setStatus(true);
            patientJPA.setUser(newUserJPA);

            PatientJPA newPatientJPA = patientRepository.save(patientJPA);
            LOGGER.info("Patient: {}, created successfully", newPatientJPA);
            return createPatientResponse(newPatientJPA);
        } catch (Exception e) {
            LOGGER.error("Error creating patient: " + e.getMessage());
            throw e;
        }

    }

    public PatientResponse getPatientById(String id) throws Exception {
        LOGGER.info("getting patient by id...");
        try {
            Optional<PatientJPA> patientJPAOptional = patientRepository.findById(id);
            PatientJPA patientJPA = patientJPAOptional.orElseThrow( () -> new Exception("Patient not found"));
            if (!patientJPA.isStatus()) throw new Exception("Patient not found");
            LOGGER.info("Patient: {}, found successfully", patientJPA);
            return createPatientResponse(patientJPA);
        } catch (Exception e) {
            LOGGER.error("Error getting patient: " + e.getMessage());
            throw e;
        }
    }

    public PatientResponse getPatientByDocument(String document) throws Exception {
        LOGGER.info("getting patient by document...");
        try {
            Optional<PatientJPA> patientJPAOptional = patientRepository.findByUserDocument(document);
            PatientJPA patientJPA = patientJPAOptional.orElseThrow( () -> new Exception("Patient not found"));
            if (!patientJPA.isStatus()) throw new Exception("Patient not found");
            LOGGER.info("Patient: {}, found successfully", patientJPA);
            return createPatientResponse(patientJPA);
        } catch (Exception e) {
            LOGGER.error("Error getting patient: " + e.getMessage());
            throw e;
        }
    }

    public List<PatientResponse> getAllPatients(){
        LOGGER.info("getting all patients...");
        try {
            List<PatientJPA> patientJPAReturn = new ArrayList<>();
            List<PatientJPA> patientJPA = patientRepository.findAll();
            patientJPA.stream().filter(PatientJPA::isStatus).forEach(patientJPAReturn::add);
            LOGGER.info("Patient found successfully");
            return createPatientResponseList(patientJPAReturn);
        } catch (Exception e) {
            LOGGER.error("Error getting patient: " + e.getMessage());
            throw e;
        }
    }

    public void deletePatient(String id) throws Exception {
        LOGGER.info("deleting patient...");
        try {
            Optional<PatientJPA> patientJPAOptional = patientRepository.findById(id);
            PatientJPA patientJPA = patientJPAOptional.orElseThrow( () -> new Exception("Patient not found"));

            patientJPA.setStatus(false);
            patientRepository.save(patientJPA);
            LOGGER.info("Patient deleted successfully");
        } catch (Exception e) {
            LOGGER.error("Error deleting patient: " + e.getMessage());
            throw e;
        }
    }

    public PatientResponse createPatientResponse(PatientJPA patientJPA){
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(patientJPA.getId());
        patientResponse.setAge(patientJPA.getAge());
        patientResponse.setCellphone(patientJPA.getCellphone());
        patientResponse.setHealthInsurance(patientJPA.getHealthInsurance());
        patientResponse.setMember(patientJPA.getMember());
        patientResponse.setName(patientJPA.getUser().getName());
        patientResponse.setSurname(patientJPA.getUser().getSurname());
        patientResponse.setDocument(patientJPA.getUser().getDocument());
        patientResponse.setEmail(patientJPA.getUser().getEmail());
        patientResponse.setCreationDate(patientJPA.getUser().getCreationDate());
        patientResponse.setRole(patientJPA.getUser().getRole());
        return patientResponse;
    }

    public List<PatientResponse> createPatientResponseList(List<PatientJPA> patientJPA){
        return patientJPA.stream().map(this::createPatientResponse).toList();
    }
}
