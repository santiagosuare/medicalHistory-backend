package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.helpers.Roles;
import com.tfg.medicalHistorybackend.models.jpa.DoctorJPA;
import com.tfg.medicalHistorybackend.models.jpa.UserJPA;
import com.tfg.medicalHistorybackend.models.responses.DoctorResponse;
import com.tfg.medicalHistorybackend.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            userJPA.setRole(Roles.DOCTOR.getId());
            UserJPA newUserJPA = userService.createUserJPA(userJPA);

            DoctorJPA doctorJPA = new DoctorJPA();
            doctorJPA.setMp(doctorResponse.getMp());
            doctorJPA.setInstitution(doctorResponse.getInstitution());
            doctorJPA.setStatus(true);
            doctorJPA.setUser(newUserJPA);

            DoctorJPA newDoctorJPA = doctorRepository.save(doctorJPA);
            LOGGER.info("Doctor: {}, created successfully", newDoctorJPA);
            return createDoctorResponse(newDoctorJPA);
        } catch (Exception e) {
            LOGGER.error("Error creating doctor: " + e.getMessage());
            throw e;
        }
    }

    public DoctorResponse getDoctorById(String id) throws Exception {
        LOGGER.info("getting doctor by id...");
        try{
            Optional<DoctorJPA> doctorJPAOptional = doctorRepository.findById(id);
            DoctorJPA doctorJPA = doctorJPAOptional.orElseThrow(() -> new Exception("Doctor not found"));
            if (!doctorJPA.isStatus()) throw new Exception("Doctor not found");
            LOGGER.info("Doctor: {}, found successfully", doctorJPA);
            return createDoctorResponse(doctorJPA);
        } catch (Exception e){
            LOGGER.error("Error getting doctor: " + e.getMessage());
            throw e;
        }
    }

    public List<DoctorResponse> getAllDoctor(){
        LOGGER.info("getting all doctors...");
        try{
            List<DoctorJPA> doctorJPAReturn = new ArrayList<>();
            List<DoctorJPA> doctorJPAList = doctorRepository.findAll();
            doctorJPAList.stream().filter(DoctorJPA::isStatus).forEach(doctorJPAReturn::add);
            LOGGER.info("Doctors: {}, found successfully", doctorJPAReturn);
            return createDoctorResponseList(doctorJPAReturn);
        } catch (Exception e){
            LOGGER.error("Error getting doctors: " + e.getMessage());
            throw e;
        }
    }

    public void deleteDoctor(String id) throws Exception {
        LOGGER.info("deleting doctor...");
        try{
            Optional<DoctorJPA> doctorJPAOptional = doctorRepository.findById(id);
            DoctorJPA doctorJPA = doctorJPAOptional.orElseThrow(() -> new Exception("Doctor not found"));
            doctorJPA.setStatus(false);
            doctorRepository.save(doctorJPA);
            LOGGER.info("Doctor: {}, deleted successfully", doctorJPA);
        }catch (Exception e) {
            LOGGER.error("Error deleting doctor: " + e.getMessage());
            throw e;
        }
    }
    private DoctorResponse createDoctorResponse(DoctorJPA doctorJPA){
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setId(doctorJPA.getId());
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

    private List<DoctorResponse> createDoctorResponseList(List<DoctorJPA> doctorJPAList){
        return doctorJPAList.stream().map(this::createDoctorResponse).toList();
    }
}
