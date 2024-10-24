package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.DTO.UserDataDTO;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import com.grocipes_backend.grocipes.services.BodyMeasurementsService;
import com.grocipes_backend.grocipes.services.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("userData/")
public class UserDataController {
    private UserDataService userDataService;
    private BodyMeasurementsService bodyMeasurementsService;

    public UserDataController(UserDataService userDataService, BodyMeasurementsService bodyMeasurementsService) {
        this.userDataService = userDataService;
        this.bodyMeasurementsService = bodyMeasurementsService;
    }

    @GetMapping("{email}")
    public UserDataDTO getUser(@PathVariable String email){
        return userDataService.getUserByEmail(email);
    }

    @PostMapping("add/bodyMeasurment")
    public ResponseEntity<Void> addBodyMeasurement(@RequestBody BodyMeasurementsDTO bodyMeasurementsDTO){
        LocalDateTime now = LocalDateTime.now();
        bodyMeasurementsDTO.setMeasurement_date(now);
        bodyMeasurementsService.save(bodyMeasurementsDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("getAll/bodyMeasurment/{userId}")
    public List<BodyMeasurementsDTO> getBodyMeasurements(@PathVariable Integer userId){
        return bodyMeasurementsService.getBodyMeasurements(userId);
    }

}
