package com.grocipes_backend.grocipes.controllers;


import com.grocipes_backend.grocipes.services.RecipeProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipeProduct")
public class RecipeProductController {

    private final RecipeProductService recipeProductService;

    public RecipeProductController(RecipeProductService recipeProductService) {
        this.recipeProductService = recipeProductService;
    }


}
