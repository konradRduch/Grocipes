package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.DTO.UserDataDTO;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import com.grocipes_backend.grocipes.services.BodyMeasurementsService;
import com.grocipes_backend.grocipes.services.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userData/")
public class UserDataController {
    private UserDataService userDataService;
    private BodyMeasurementsService bodyMeasurementsService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("{email}")
    public UserDataDTO getUser(@PathVariable String email){
        return userDataService.getUserByEmail(email);
    }

    @PostMapping("add/bodyMeasurment")
    public ResponseEntity<Void> bodyMeasurement(@RequestBody BodyMeasurementsDTO bodyMeasurementsDTO){



        return ResponseEntity.ok().build();
    }

}
