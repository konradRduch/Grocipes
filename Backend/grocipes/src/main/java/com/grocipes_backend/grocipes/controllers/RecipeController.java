package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.*;
import com.grocipes_backend.grocipes.models.Product;
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

    @GetMapping("/{id}")
    public GetRecipesDTO getRecipe(@PathVariable Integer id){
        List<GetRecipesDTO>recipesDTOS = recipeService.getRecipes();
        // Znajdź produkt o podanym ID
        GetRecipesDTO foundRecipe = recipesDTOS.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null); // Zwróć null, jeśli nie znaleziono
        return foundRecipe;
    }
    @GetMapping("")
    public List<GetRecipesDTO>getRecipes(){
        return recipeService.getRecipes();
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addRecipe(@RequestBody RecipeCreationDTO request){
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

        return ResponseEntity.ok().build();
    }


    @PatchMapping("edit/{id}")
    public ResponseEntity<Void> editRecipe(@PathVariable Integer id, @RequestBody RecipeCreationDTO updatedProduct) {
        // Znajdź istniejący produkt po jego ID
        Recipe recipe = recipeService.findById(id);

        // Aktualizuj dane produktu
        RecipeDTO recipeDTO = updatedProduct.getRecipe();
        recipe.setId(id);
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setPreparation_method(recipeDTO.getPreparation_method());
        recipe.setImage_url(recipeDTO.getImage_url());
        recipe.setTypeOfMeal(recipeDTO.getTypeOfMeal());
        recipeService.save(recipe);

        // Usuwanie produktów
        recipeProductService.deleteRecipeProductByProductId(id);

        // Dodawanie nowych wartości odżywczych
        for (RecipeProductDTO recipeProductDTO : updatedProduct.getProducts()) {
            recipeProductDTO.setRecipe_id(id);
            recipeProductService.addRecipeProduct(recipeProductDTO);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {
        // Najpierw usuń powiązane rekordy NutritionFactNutrient
        recipeProductService.deleteRecipeProductByProductId(id);


        recipeService.deleteRecipeById(id);

        return ResponseEntity.ok().build();
    }


}
