package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.NutritionFactNutrientDTO;
import com.grocipes_backend.grocipes.services.NutritionFactNutrientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutritionFactNutrient")
public class NutritionFactNutrientController {
    private final NutritionFactNutrientService nutritionFactNutrientService;

    public NutritionFactNutrientController(NutritionFactNutrientService nutritionFactNutrientService) {
        this.nutritionFactNutrientService = nutritionFactNutrientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNutrient(@RequestBody NutritionFactNutrientDTO request) {
        // Zapis encji w serwisie
        nutritionFactNutrientService.addNutrionFactNutrient(request);

        return ResponseEntity.ok().build();
    }
}
