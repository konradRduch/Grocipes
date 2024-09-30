package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> getRecipeById(Integer id){
        return recipeRepository.findById(id);
    }
    public List<Recipe> getRecipeByTitle(String title){
        return recipeRepository.findByTitle(title);
    }
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }
}
