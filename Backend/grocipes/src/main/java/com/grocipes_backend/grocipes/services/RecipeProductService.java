package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.*;
import com.grocipes_backend.grocipes.models.DTO.NutritionFactNutrientDTO;
import com.grocipes_backend.grocipes.models.DTO.RecipeProductDTO;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import com.grocipes_backend.grocipes.repositories.RecipeProductRepository;
import com.grocipes_backend.grocipes.repositories.RecipeRepository;
import com.grocipes_backend.grocipes.repositories.UnitRecipeProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeProductService {
    private final RecipeProductRepository recipeProductRepository;
    private final ProductRepository productRepository;
    private final UnitRecipeProductRepository unitRecipeProductRepository;
    private final RecipeRepository recipeRepository;

    public RecipeProductService(RecipeProductRepository recipeProductRepository,
                                ProductRepository productRepository,
                                UnitRecipeProductRepository unitRecipeProductRepository,
                                RecipeRepository recipeRepository) {
        this.recipeProductRepository = recipeProductRepository;
        this.productRepository = productRepository;
        this.unitRecipeProductRepository = unitRecipeProductRepository;
        this.recipeRepository = recipeRepository;
    }

    public Optional<RecipeProduct> addRecipeProduct(RecipeProductDTO dto) {

       //sprawdzic czy jednostka istnieje w bazie, przepis i produkt
        Recipe recipe = recipeRepository.findById(dto.getRecipe_id())
                .orElseThrow(() ->new RuntimeException("Recipe not found"));
        Product product = productRepository.findById(dto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        UnitRecipeProduct unitRecipeProduct = unitRecipeProductRepository.findById(dto.getUnit_id())
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        RecipeProduct entity = new RecipeProduct(dto.getQuantity(),recipe,product,unitRecipeProduct);
        return Optional.of(recipeProductRepository.save(entity));
    }

    @Transactional
    public void deleteRecipeProductByProductId(Integer id) {
        recipeProductRepository.deleteByRecipeId(id);
    }
}
