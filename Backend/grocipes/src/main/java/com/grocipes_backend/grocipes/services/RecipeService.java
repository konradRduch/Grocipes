package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.*;
import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.repositories.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Optional<Recipe> addRecipe(RecipeDTO dto){

        if (recipeRepository.existsByTitle(dto.getTitle())) {
            // Jeśli przepis istnieje, zwracamy pustą wartość
            return Optional.empty();
        }

        Recipe recipe = new Recipe(dto.getTitle(),dto.getDescription(),dto.getPreparation_method(),dto.getImage_url(), dto.getTypeOfMeal());
        return Optional.of(recipeRepository.save(recipe));
    }

    public Integer getRecipeIdByTitle(String title){
        List<Recipe> recipes = recipeRepository.findByTitle(title);
        if(recipes.isEmpty()){
            throw new RuntimeException("Recipe not found");
        }
        return recipes.get(0).getId();
    }

    public List<GetRecipesDTO> getRecipes(){
        List<Object[]> objects = recipeRepository.findRecipes();
        Map<Integer, GetRecipesDTO> recipesMap = new HashMap<>();

        for (Object[] o : objects) {
            Integer recipeId = (Integer) o[0];

            // Jeśli produkt już istnieje na mapie, użyj go, jeśli nie, stwórz nowy obiekt GetProductsDTO
            GetRecipesDTO getRecipesDTO = recipesMap.getOrDefault(recipeId,
                    new GetRecipesDTO(
                            recipeId,
                            (String) o[1],
                            (String) o[2],
                            (String) o[3],
                            (String) o[4],
                            (String) o[5],
                            new ArrayList<>()
                    )
            );

            ProductWithUnitDTO productWithUnitDTO = new ProductWithUnitDTO(
                    (Integer) o[6],//6
                    (String) o[7],//7
                    (double) o[8],//8
                    (double) o[9],//9
                    (String) o[10],//10
                    (Integer) o[11],//11
                    (Integer) o[13],//13
                    (String)o[14],//14
                    (double)o[12]//12
            );

            // Dodajemy nutrientDTO do listy w GetProductsDTO
            getRecipesDTO.getProducts().add(productWithUnitDTO);

            // Zapisujemy zaktualizowany obiekt do mapy (jeśli jest nowy)
            recipesMap.put(recipeId, getRecipesDTO);
        }
        return new ArrayList<>(recipesMap.values());
    }
    @Transactional
    public void deleteRecipeById(Integer id) {
        recipeRepository.deleteById(id);
    }

    public Recipe findById(Integer id){
        return this.recipeRepository.findRecipeById(id);
    }
    public void save(Recipe recipe){
        this.recipeRepository.save(recipe);
    }
}













