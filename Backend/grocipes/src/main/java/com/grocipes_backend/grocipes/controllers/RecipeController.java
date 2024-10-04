package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.GetRecipesDTO;
import com.grocipes_backend.grocipes.models.DTO.RecipeCreationDTO;
import com.grocipes_backend.grocipes.models.DTO.RecipeDTO;
import com.grocipes_backend.grocipes.models.DTO.RecipeProductDTO;
import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.services.RecipeProductService;
import com.grocipes_backend.grocipes.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    private final RecipeProductService recipeProductService;

    public RecipeController(RecipeService recipeService, RecipeProductService recipeProductService) {
        this.recipeService = recipeService;
        this.recipeProductService = recipeProductService;
    }

    @GetMapping("")
    public List<GetRecipesDTO>getRecipes(){
        return recipeService.getRecipes();
    }

    @PostMapping("/add")
    public String addRecipe(@RequestBody RecipeCreationDTO request){
        RecipeDTO recipe = request.getRecipe();
        List<RecipeProductDTO> recipeProductDTOS = request.getProducts();

        recipeService.addRecipe(recipe).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

        Integer recipeId = recipeService.getRecipeIdByTitle(recipe.getTitle());
        for(int i = 0 ;i<recipeProductDTOS.size() ;i++){
        recipeProductDTOS.get(i).setRecipe_id(recipeId);
        }


        for(int i = 0 ;i<recipeProductDTOS.size() ;i++){
        recipeProductService.addRecipeProduct(recipeProductDTOS.get(i))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return "redirect:/recipes";
    }


}
