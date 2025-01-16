package com.grocipes_backend.grocipes.controllers;


import com.grocipes_backend.grocipes.models.DTO.*;
import com.grocipes_backend.grocipes.security.JWTGenerator;
import com.grocipes_backend.grocipes.services.BodyMeasurementsService;
import com.grocipes_backend.grocipes.services.NutritionalGoalService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/{id}")
    public NutritionalGoalDTO getNutritionalGoal(@PathVariable Integer id){
        return nutritionalGoalService.findGoalById(id);
    }
    @GetMapping("/getAll")
    public List<NutritionalGoalDTO> getNutritionalGoals(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            ProfileInfoDTO profileInfoDTO = bodyMeasurementsService.getUserProfileInfo(userId);
            return nutritionalGoalService.findAllGoals(userId,profileInfoDTO);
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }
    @PostMapping("/getEstimatedTimeToAchieveGoal")
    public double estimatedTimeToAchieveGoal(HttpServletRequest request, @RequestBody EstimateTimeDTO estimateTimeDTO){
       return nutritionalGoalService.estimatedTimeToAchieveGoal(estimateTimeDTO.getCurrentWeight(),estimateTimeDTO.getTargetWeight(),estimateTimeDTO.getTypeOfGoal());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNutritionalGoal(HttpServletRequest request, @RequestBody NutritionalGoalDTO nutritionalGoalDTO){

        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            ProfileInfoDTO profileInfoDTO = bodyMeasurementsService.getUserProfileInfo(userId);
            //nutritionalGoalService.cos(profileInfoDTO);
            nutritionalGoalService.addNutritionalGoal(userId, nutritionalGoalDTO,profileInfoDTO);

            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNutritionalGoal(@PathVariable Integer id){
        //usuwanie potem jeszcze daily demand
        nutritionalGoalService.deleteNutritionalGoal(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/edit/{id}")
    public ResponseEntity<Void> editNutritionalGoal(@PathVariable Integer id){

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getGoalDailyDemand")
    public DailyDemandDTO getGoalDailyDemand(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            ProfileInfoDTO profileInfoDTO = bodyMeasurementsService.getUserProfileInfo(userId);
            return nutritionalGoalService.getGoalDailyDemand(userId, profileInfoDTO);
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }

}
