package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.Nutrient;
import com.grocipes_backend.grocipes.services.NutrientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutrients")
public class NutrientController {
    private final NutrientService nutrientService;

    public NutrientController(NutrientService nutrientService) {
        this.nutrientService = nutrientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Nutrient> addNutrient(@RequestBody Nutrient nutrient){
        return nutrientService.addNutrient(nutrient).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
