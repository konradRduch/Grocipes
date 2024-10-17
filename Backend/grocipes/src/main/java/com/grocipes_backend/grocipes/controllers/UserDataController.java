package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.UserDataDTO;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import com.grocipes_backend.grocipes.services.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userData/")
public class UserDataController {
    private UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("{email}")
    public UserDataDTO getUser(@PathVariable String email){
        return userDataService.getUserByEmail(email);
    }

}
