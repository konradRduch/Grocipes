package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.ProfileInfoDTO;
import com.grocipes_backend.grocipes.security.JWTGenerator;
import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.DTO.UserDataDTO;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import com.grocipes_backend.grocipes.services.BodyMeasurementsService;
import com.grocipes_backend.grocipes.services.UserDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("userData/")
public class UserDataController {
    private UserDataService userDataService;
    private JWTGenerator jwtGenerator;
    private BodyMeasurementsService bodyMeasurementsService;

    public UserDataController(UserDataService userDataService, JWTGenerator jwtGenerator, BodyMeasurementsService bodyMeasurementsService) {
        this.userDataService = userDataService;
        this.jwtGenerator = jwtGenerator;
        this.bodyMeasurementsService = bodyMeasurementsService;
    }

    @GetMapping("getUser")
    public UserDataDTO getUser(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            return userDataService.getUserById(userId);
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }

    @PostMapping("add/bodyMeasurment")
    public ResponseEntity<Void> addBodyMeasurement(@RequestBody BodyMeasurementsDTO bodyMeasurementsDTO,HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            bodyMeasurementsDTO.setUserId(userId);

            LocalDateTime now = LocalDateTime.now();
            bodyMeasurementsDTO.setMeasurement_date(now);

            bodyMeasurementsService.save(bodyMeasurementsDTO);
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }
    @GetMapping("getAll/bodyMeasurment")
    public List<BodyMeasurementsDTO> getBodyMeasurements(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            return bodyMeasurementsService.getBodyMeasurements(userId);
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }
    @GetMapping("getUserProfileInfo")
    public ProfileInfoDTO getUserProfileInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            return bodyMeasurementsService.getUserProfileInfo(userId);
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }

}
