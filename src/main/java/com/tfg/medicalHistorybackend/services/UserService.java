package com.tfg.medicalHistorybackend.services;

import com.tfg.medicalHistorybackend.models.jpa.UserJPA;
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
}
