package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.jpa.DoctorJPA;
import com.tfg.medicalHistorybackend.models.jpa.MedicalHistoryJPA;
import com.tfg.medicalHistorybackend.models.jpa.MedicalStaffJPA;
import com.tfg.medicalHistorybackend.models.jpa.PatientJPA;
import com.tfg.medicalHistorybackend.models.responses.MedicalHistoryResponse;
import com.tfg.medicalHistorybackend.repository.DoctorRepository;
import com.tfg.medicalHistorybackend.repository.MedicalHistoryRepository;
import com.tfg.medicalHistorybackend.repository.MedicalStaffRepository;
import com.tfg.medicalHistorybackend.repository.PatientRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    private final DoctorService  doctorService;

    private final MedicalStaffRepository medicalStaffRepository;

    private final MedicalStaffService medicalStaffService;

    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryService(PatientRepository patientRepository,
                                 DoctorRepository doctorRepository,
                                 DoctorService doctorService,
                                 MedicalStaffRepository medicalStaffRepository,
                                 MedicalStaffService medicalStaffService,
                                 MedicalHistoryRepository medicalHistoryRepository){
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.doctorService = doctorService;
        this.medicalStaffRepository = medicalStaffRepository;
        this.medicalStaffService = medicalStaffService;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public MedicalHistoryResponse createMedicalHistory(MedicalHistoryResponse medicalHistoryResponse) throws Exception {
        LOGGER.info("creating medical history...");
        try{
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

            MedicalHistoryJPA medicalHistoryJPA = new MedicalHistoryJPA();
            medicalHistoryJPA.setCreationDate(zonedDateTime);
            medicalHistoryJPA.setDiagnostic(medicalHistoryResponse.getDiagnostic());
            medicalHistoryJPA.setStudyRequest(medicalHistoryResponse.getStudyRequest());
            medicalHistoryJPA.setOrderPrescription(medicalHistoryResponse.getOrderPrescription());

            if (!medicalHistoryResponse.getPatientId().isEmpty()){
                Optional<PatientJPA> patientJPAOptional = patientRepository.findById(medicalHistoryResponse.getPatientId());
                PatientJPA patientJPA = patientJPAOptional.orElseThrow( () -> new Exception("Patient not found"));
                medicalHistoryJPA.setPatient(patientJPA);
            }

            if (medicalHistoryResponse.getDoctor() != null){
                Optional<DoctorJPA> doctorJPAOptional = doctorRepository.findById(String.valueOf(medicalHistoryResponse.getDoctor().getId()));
                DoctorJPA doctorJPA = doctorJPAOptional.orElseThrow( () -> new Exception("Doctor not found"));
                medicalHistoryJPA.setDoctor(doctorJPA);
            }

            if (medicalHistoryResponse.getMedicalStaff() != null) {
                Optional<MedicalStaffJPA> medicalStaffJPAOptional = medicalStaffRepository.findById(String.valueOf(medicalHistoryResponse.getMedicalStaff().getId()));
                MedicalStaffJPA medicalStaffJPA = medicalStaffJPAOptional.orElseThrow(() -> new Exception("Medical staff not found"));
                medicalHistoryJPA.setMedicalStaff(medicalStaffJPA);
            }

            medicalHistoryRepository.save(medicalHistoryJPA);
            LOGGER.info("Medical history, created successfully");

            return medicalHistoryResponse;
        } catch (Exception e) {
            LOGGER.error("Error creating medical history: " + e.getMessage());
            throw e;
        }
    }

    public List<MedicalHistoryResponse> getMedicalHistoryByPatientId(String patientId){
        LOGGER.info("getting medical history by patient id...");
        try{
            List<MedicalHistoryJPA> medicalHistoryJPAList = medicalHistoryRepository.findByPatientId(Long.parseLong(patientId));
            List<MedicalHistoryResponse> medicalHistoryResponseList = new ArrayList<>();

            medicalHistoryJPAList.forEach(medicalHistory -> {
                MedicalHistoryResponse medicalHistoryResponse = new MedicalHistoryResponse();
                medicalHistoryResponse.setId(medicalHistory.getId());
                medicalHistoryResponse.setCreationDate(medicalHistory.getCreationDate());
                medicalHistoryResponse.setDiagnostic(medicalHistory.getDiagnostic());
                medicalHistoryResponse.setStudyRequest(medicalHistory.getStudyRequest());
                medicalHistoryResponse.setOrderPrescription(medicalHistory.getOrderPrescription());
                if(medicalHistory.getDoctor() != null ){
                    medicalHistoryResponse.setDoctor(doctorService.createDoctorResponse(medicalHistory.getDoctor()));
                } else {
                    medicalHistoryResponse.setMedicalStaff(medicalStaffService.createMedicalStaffResponse(medicalHistory.getMedicalStaff()));
                }

                medicalHistoryResponseList.add(medicalHistoryResponse);
            });
            return medicalHistoryResponseList;

        } catch (Exception e) {
            LOGGER.error("Error getting medical history by patient id: " + e.getMessage());
            throw e;
        }
    }


}
