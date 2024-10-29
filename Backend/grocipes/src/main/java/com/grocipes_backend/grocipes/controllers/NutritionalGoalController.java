package com.grocipes_backend.grocipes.controllers;


import com.grocipes_backend.grocipes.models.DTO.ProfileInfoDTO;
import com.grocipes_backend.grocipes.models.DTO.ShoppingScheduleDTO;
import com.grocipes_backend.grocipes.security.JWTGenerator;
import com.grocipes_backend.grocipes.services.BodyMeasurementsService;
import com.grocipes_backend.grocipes.services.NutritionalGoalService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.apache.commons.lang3.tuple.Pair;

@RestController
@RequestMapping("/nutritionalGoal")
public class NutritionalGoalController {

    private NutritionalGoalService nutritionalGoalService;
    private BodyMeasurementsService bodyMeasurementsService;

    private JWTGenerator jwtGenerator;

    public NutritionalGoalController(NutritionalGoalService nutritionalGoalService, BodyMeasurementsService bodyMeasurementsService, JWTGenerator jwtGenerator) {
        this.nutritionalGoalService = nutritionalGoalService;
        this.bodyMeasurementsService = bodyMeasurementsService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNutritionalGoal(HttpServletRequest request){

        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            ProfileInfoDTO profileInfoDTO = bodyMeasurementsService.getUserProfileInfo(userId);
            nutritionalGoalService.cos(profileInfoDTO);


            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }



}
