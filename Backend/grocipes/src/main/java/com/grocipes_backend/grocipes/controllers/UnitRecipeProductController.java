package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.Nutrient;
import com.grocipes_backend.grocipes.models.UnitRecipeProduct;
import com.grocipes_backend.grocipes.services.UnitRecipeProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unitRecipeProduct")
public class UnitRecipeProductController {

    private final UnitRecipeProductService unitRecipeProductService;

    public UnitRecipeProductController(UnitRecipeProductService unitRecipeProductService) {
        this.unitRecipeProductService = unitRecipeProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<UnitRecipeProduct> addNutrient(@RequestBody UnitRecipeProduct request){
        return unitRecipeProductService.addUnit(request).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
