package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.NutritionalGoalDTO;
import com.grocipes_backend.grocipes.models.DTO.ProfileInfoDTO;
import com.grocipes_backend.grocipes.models.DTO.RecipeDTO;
import com.grocipes_backend.grocipes.security.JWTGenerator;
import com.grocipes_backend.grocipes.services.RecipeSuggestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipeSuggestions")
public class RecipeSuggestionController {

    private final RecipeSuggestionService recipeSuggestionService;
    private JWTGenerator jwtGenerator;

    public RecipeSuggestionController(RecipeSuggestionService recipeSuggestionService, JWTGenerator jwtGenerator) {
        this.recipeSuggestionService = recipeSuggestionService;
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/getSuggestionRecipeList")
    public List<RecipeDTO> getNutritionalGoals(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            List<RecipeDTO> suggestedRecipes = recipeSuggestionService.getSuggestionsForUser(userId);
            return suggestedRecipes;
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }


}
