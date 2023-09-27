package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.helpers.Roles;
import com.tfg.medicalHistorybackend.models.jpa.*;
import com.tfg.medicalHistorybackend.models.responses.*;
import com.tfg.medicalHistorybackend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class PatientService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final CUSRepository cusRepository;
    private final VaccineRepository vaccineRepository;
    private final PhysicalExaminationRepository physicalExaminationRepository;
    private final PathologicalHistoryRepository pathologicalHistoryRepository;

    private final PrescribedMedicationRepository prescribedMedicationRepository;

    public PatientService(PatientRepository patientRepository,
                          UserRepository userRepository,
                          CUSRepository cusRepository,
                          VaccineRepository vaccineRepository,
                          PhysicalExaminationRepository physicalExaminationRepository,
                          PathologicalHistoryRepository pathologicalHistoryRepository,
                          PrescribedMedicationRepository prescribedMedicationRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.cusRepository = cusRepository;
        this.vaccineRepository = vaccineRepository;
        this.physicalExaminationRepository = physicalExaminationRepository;
        this.pathologicalHistoryRepository = pathologicalHistoryRepository;
        this.prescribedMedicationRepository = prescribedMedicationRepository;
    }

    public UserResponse createPatient(UserResponse user){
        LOGGER.info("creating patient...");

        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

            UserJPA userJPA = new UserJPA();
            userJPA.setName(user.getName());
            userJPA.setSurname(user.getSurname());
            userJPA.setDocument(user.getDocument());
            userJPA.setEmail(user.getEmail());
            userJPA.setCreationDate(zonedDateTime);
            userJPA.setRole(Roles.PATIENT.getId());
            userJPA.setPassword("123456");

            PatientJPA patientJPA = new PatientJPA();
            patientJPA.setAge(user.getPatient().getAge());
            patientJPA.setCellphone(user.getPatient().getCellphone());
            patientJPA.setHealthInsurance(user.getPatient().getHealthInsurance());
            patientJPA.setMember(user.getPatient().getMember());
            patientJPA.setStatus(true);
            patientJPA.setUser(userJPA);

            CUSJPA cusJPA = new CUSJPA();
            cusJPA.setBirthDate(user.getPatient().getCus().getBirthDate());
            cusJPA.setGender(user.getPatient().getCus().getGender());
            cusJPA.setAddress(user.getPatient().getCus().getAddress());
            cusJPA.setCity(user.getPatient().getCus().getCity());
            cusJPA.setPatient(patientJPA);

            VaccineJPA vaccineJPA = new VaccineJPA();
            vaccineJPA.setCard(user.getPatient().getCus().getVaccine().isCard());
            vaccineJPA.setComplete(user.getPatient().getCus().getVaccine().isComplete());
            vaccineJPA.setObservations(user.getPatient().getCus().getVaccine().getObservations());
            vaccineJPA.setCus(cusJPA);

            PhysicalExaminationJPA physicalExaminationJPA = new PhysicalExaminationJPA();
            physicalExaminationJPA.setSize(user.getPatient().getCus().getPhysicalExamination().getSize());
            physicalExaminationJPA.setWeight(user.getPatient().getCus().getPhysicalExamination().getWeight());
            physicalExaminationJPA.setBmi(user.getPatient().getCus().getPhysicalExamination().getBmi());
            physicalExaminationJPA.setObservations(user.getPatient().getCus().getPhysicalExamination().getObservations());
            physicalExaminationJPA.setCus(cusJPA);

            PathologicalHistoryJPA pathologicalHistoryJPA = new PathologicalHistoryJPA();
            pathologicalHistoryJPA.setSurgeries(user.getPatient().getCus().getPathologicalHistory().getSurgeries());
            pathologicalHistoryJPA.setCardiovascular(user.getPatient().getCus().getPathologicalHistory().getCardiovascular());
            pathologicalHistoryJPA.setAllergies(user.getPatient().getCus().getPathologicalHistory().getAllergies());
            pathologicalHistoryJPA.setOftalmologicos(user.getPatient().getCus().getPathologicalHistory().getOftalmologicos());
            pathologicalHistoryJPA.setOthers(user.getPatient().getCus().getPathologicalHistory().getOthers());
            pathologicalHistoryJPA.setCus(cusJPA);

            PrescribedMedicationJPA prescribedMedicationJPA = new PrescribedMedicationJPA();
            prescribedMedicationJPA.setMedication(user.getPatient().getCus().getPrescribedMedication().getMedication());
            prescribedMedicationJPA.setCus(cusJPA);

            userRepository.save(userJPA);
            patientRepository.save(patientJPA);
            cusRepository.save(cusJPA);
            vaccineRepository.save(vaccineJPA);
            physicalExaminationRepository.save(physicalExaminationJPA);
            pathologicalHistoryRepository.save(pathologicalHistoryJPA);
            prescribedMedicationRepository.save(prescribedMedicationJPA);

            LOGGER.info("Patient, created successfully");
            return user;
        } catch (Exception e){
            LOGGER.error("Error creating patient: " + e);
            throw e;
        }


    }

    public UserResponse getPatientById(Long id) throws Exception {
        LOGGER.info("getting patient by id...");
        try {
            Optional<PatientJPA> patientJPAOptional = patientRepository.findById(String.valueOf(id));
            PatientJPA patientJPA = patientJPAOptional.orElseThrow( () -> new Exception("Patient not found"));

            Optional<CUSJPA> cusJPAOptional = cusRepository.findByPatientId(String.valueOf(patientJPA.getId()));
            CUSJPA cusJPA = cusJPAOptional.orElseThrow( () -> new Exception("CUS not found"));

            Optional<VaccineJPA> vaccineJPAOptional = vaccineRepository.findByCUSId(String.valueOf(cusJPA.getId()));
            VaccineJPA vaccineJPA = vaccineJPAOptional.orElseThrow( () -> new Exception("vaccine not found"));

            Optional<PhysicalExaminationJPA> physicalExaminationJPAOptional = physicalExaminationRepository.findByCUSId(String.valueOf(cusJPA.getId()));
            PhysicalExaminationJPA physicalExaminationJPA = physicalExaminationJPAOptional.orElseThrow( () -> new Exception("PhysicalExamination not found"));

            Optional<PathologicalHistoryJPA> pathologicalHistoryJPAOptional = pathologicalHistoryRepository.findByCUSId(String.valueOf(cusJPA.getId()));
            PathologicalHistoryJPA pathologicalHistoryJPA = pathologicalHistoryJPAOptional.orElseThrow( () -> new Exception("Pathological History not found"));

            Optional<PrescribedMedicationJPA> prescribedMedicationJPAOptional = prescribedMedicationRepository.findByCUSId(String.valueOf(cusJPA.getId()));
            PrescribedMedicationJPA prescribedMedicationJPA = prescribedMedicationJPAOptional.orElseThrow( () -> new Exception("Prescribed Medication not found"));

            PrescribedMedicationResponse prescribedMedicationResponse = new PrescribedMedicationResponse();
            prescribedMedicationResponse.setMedication(prescribedMedicationJPA.getMedication());

            VaccineResponse vaccineResponse = new VaccineResponse();
            vaccineResponse.setCard(vaccineJPA.isCard());
            vaccineResponse.setComplete(vaccineJPA.isComplete());
            vaccineResponse.setObservations(vaccineJPA.getObservations());

            PhysicalExaminationResponse physicalExaminationResponse = new PhysicalExaminationResponse();
            physicalExaminationResponse.setSize(physicalExaminationJPA.getSize());
            physicalExaminationResponse.setWeight(physicalExaminationJPA.getWeight());
            physicalExaminationResponse.setBmi(physicalExaminationJPA.getBmi());
            physicalExaminationResponse.setObservations(physicalExaminationJPA.getObservations());

            PathologicalHistoryResponse pathologicalHistoryResponse = new PathologicalHistoryResponse();
            pathologicalHistoryResponse.setSurgeries(pathologicalHistoryJPA.getSurgeries());
            pathologicalHistoryResponse.setCardiovascular(pathologicalHistoryJPA.getCardiovascular());
            pathologicalHistoryResponse.setAllergies(pathologicalHistoryJPA.getAllergies());
            pathologicalHistoryResponse.setOftalmologicos(pathologicalHistoryJPA.getOftalmologicos());
            pathologicalHistoryResponse.setOthers(pathologicalHistoryJPA.getOthers());

            CUSResponse cusResponse = new CUSResponse();
            cusResponse.setBirthDate(cusJPA.getBirthDate());
            cusResponse.setGender(cusJPA.getGender());
            cusResponse.setAddress(cusJPA.getAddress());
            cusResponse.setCity(cusJPA.getCity());
            cusResponse.setVaccine(vaccineResponse);
            cusResponse.setPhysicalExamination(physicalExaminationResponse);
            cusResponse.setPathologicalHistory(pathologicalHistoryResponse);
            cusResponse.setPrescribedMedication(prescribedMedicationResponse);

            PatientResponse patientResponse = new PatientResponse();
            patientResponse.setAge(patientJPA.getAge());
            patientResponse.setCellphone(patientJPA.getCellphone());
            patientResponse.setHealthInsurance(patientJPA.getHealthInsurance());
            patientResponse.setMember(patientJPA.getMember());
            patientResponse.setCus(cusResponse);

            UserResponse userResponse = new UserResponse();
            userResponse.setName(patientJPA.getUser().getName());
            userResponse.setSurname(patientJPA.getUser().getSurname());
            userResponse.setDocument(patientJPA.getUser().getDocument());
            userResponse.setEmail(patientJPA.getUser().getEmail());
            userResponse.setPatient(patientResponse);

            LOGGER.info("Patient, found successfully");
            return userResponse;

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

}
