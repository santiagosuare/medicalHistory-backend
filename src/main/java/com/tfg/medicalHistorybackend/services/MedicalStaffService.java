package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.helpers.Roles;
import com.tfg.medicalHistorybackend.models.jpa.MedicalStaffJPA;
import com.tfg.medicalHistorybackend.models.jpa.UserJPA;
import com.tfg.medicalHistorybackend.models.responses.MedicalStaffResponse;
import com.tfg.medicalHistorybackend.repository.MedicalStaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final MedicalStaffRepository medicalStaffRepository;
    private final UserService userService;

    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository, UserService userService){
        this.medicalStaffRepository = medicalStaffRepository;
        this.userService = userService;
    }

    public MedicalStaffResponse createMedicalStaff(MedicalStaffResponse medicalStaffResponse){
        LOGGER.info("creating medical staff...");
        try{
            UserJPA userJPA = new UserJPA();
            userJPA.setName(medicalStaffResponse.getName());
            userJPA.setSurname(medicalStaffResponse.getSurname());
            userJPA.setDocument(medicalStaffResponse.getDocument());
            userJPA.setEmail(medicalStaffResponse.getEmail());
            userJPA.setRole(Roles.MEDICAL_STAFF.getId());
            UserJPA newUserJPA = userService.createUserJPA(userJPA);

            MedicalStaffJPA medicalStaffJPA = new MedicalStaffJPA();
            medicalStaffJPA.setMp(medicalStaffResponse.getMp());
            medicalStaffJPA.setInstitution(medicalStaffResponse.getInstitution());
            medicalStaffJPA.setStatus(true);
            medicalStaffJPA.setUser(newUserJPA);

            MedicalStaffJPA newMedicalStaffJPA = medicalStaffRepository.save(medicalStaffJPA);
            LOGGER.info("Medical staff: {}, created successfully", newMedicalStaffJPA);
            return createMedicalStaffResponse(newMedicalStaffJPA);
        } catch (Exception e) {
            LOGGER.error("Error creating medical staff: " + e.getMessage());
            throw e;
        }
    }

    public MedicalStaffResponse getMedicalStaffById(String id) throws Exception {
        LOGGER.info("getting medical staff by id...");
        try {
            Optional<MedicalStaffJPA> medicalStaffJPAOptional = medicalStaffRepository.findById(id);
            MedicalStaffJPA medicalStaffJPA = medicalStaffJPAOptional.orElseThrow(() -> new Exception("Medical staff not found"));
            if(!medicalStaffJPA.isStatus()) throw new Exception("Medical staff is not active");
            LOGGER.info("Medical staff: {}, found successfully", medicalStaffJPA);
            return createMedicalStaffResponse(medicalStaffJPA);
        } catch (Exception e) {
            LOGGER.error("Error getting medical staff: " + e.getMessage());
            throw e;
        }
    }

    public List<MedicalStaffResponse> getAllMedicalStaff(){
        LOGGER.info("getting all medical staff...");
        try {
            List<MedicalStaffJPA> medicalStaffJPAReturn = new ArrayList<>();
            List<MedicalStaffJPA> medicalStaffJPAList = medicalStaffRepository.findAll();
            medicalStaffJPAList.stream().filter(MedicalStaffJPA::isStatus).forEach(medicalStaffJPAReturn::add);
            LOGGER.info("Medical staff list: {}, found successfully", medicalStaffJPAReturn);
            return createMedicalStaffResponseList(medicalStaffJPAReturn);
        } catch (Exception e) {
            LOGGER.error("Error getting medical staff list: " + e.getMessage());
            throw e;
        }
    }

    public void deleteMedicalStaff(String id) throws Exception {
        LOGGER.info("deleting medical staff...");
        try{
            Optional<MedicalStaffJPA> medicalStaffJPAOptional = medicalStaffRepository.findById(id);
            MedicalStaffJPA medicalStaffJPA = medicalStaffJPAOptional.orElseThrow(() -> new Exception("Medical staff not found"));

            medicalStaffJPA.setStatus(false);
            medicalStaffRepository.save(medicalStaffJPA);
            LOGGER.info("Medical staff: {}, deleted successfully", medicalStaffJPA);
        } catch (Exception e) {
            LOGGER.error("Error deleting medical staff: " + e.getMessage());
            throw e;
        }
    }

    public MedicalStaffResponse createMedicalStaffResponse(MedicalStaffJPA medicalStaffJPA){
        MedicalStaffResponse medicalStaffResponse = new MedicalStaffResponse();
        medicalStaffResponse.setId(medicalStaffJPA.getId());
        medicalStaffResponse.setMp(medicalStaffJPA.getMp());
        medicalStaffResponse.setInstitution(medicalStaffJPA.getInstitution());
        medicalStaffResponse.setName(medicalStaffJPA.getUser().getName());
        medicalStaffResponse.setSurname(medicalStaffJPA.getUser().getSurname());
        medicalStaffResponse.setDocument(medicalStaffJPA.getUser().getDocument());
        medicalStaffResponse.setEmail(medicalStaffJPA.getUser().getEmail());
        medicalStaffResponse.setRole(medicalStaffJPA.getUser().getRole());
        return medicalStaffResponse;
    }

    public List<MedicalStaffResponse> createMedicalStaffResponseList(List<MedicalStaffJPA> medicalStaffJPAList){
        return medicalStaffJPAList.stream().map(this::createMedicalStaffResponse).toList();
    }
}
