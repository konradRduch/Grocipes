package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("")
    public List<Recipe>getRecipes(){
        return recipeService.getAllRecipes();
    }



}
