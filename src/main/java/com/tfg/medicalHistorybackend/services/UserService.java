package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.jpa.UserJPA;
import com.tfg.medicalHistorybackend.models.responses.LoginResponse;
import com.tfg.medicalHistorybackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public UserJPA createUserJPA(UserJPA userJPA){
        LOGGER.info("creating user...");

        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));

            userJPA.setCreationDate(zonedDateTime);
            UserJPA userJPAnew = userRepository.save(userJPA);

            LOGGER.info("User: {}, created successfully", userJPAnew);
            return userJPAnew;

        } catch (Exception e) {
            LOGGER.error("Error to create user: " + e.getMessage());
            throw e;
        }
    }

    public LoginResponse loginUser(String document, String password){
        LOGGER.info("login user...");
        try{
            UserJPA userJPA = userRepository.findByDocument(document).orElseThrow(() -> new Exception("User not found"));
            if(userJPA.getPassword().equals(password)){
                LOGGER.info("User: {}, logged successfully", userJPA);
                return createLoginResponse(userJPA, true);
            }else{
                LOGGER.info("User: {}, not logged", userJPA);
                return createLoginResponse(userJPA, false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private LoginResponse createLoginResponse(UserJPA userJPA, boolean logged){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(userJPA.getId());
        loginResponse.setRole(userJPA.getRole());
        loginResponse.setLogged(logged);
        return loginResponse;
    }
}
