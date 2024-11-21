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
        double price = calculateIngredientPrice(product.getWeight(),product.getUnit().getName(), product.getPrice(), entity.getUnit().getName(), entity.getQuantity());
        entity.setPrice(price);
        return Optional.of(recipeProductRepository.save(entity));
    }

    @Transactional
    public void deleteRecipeProductByProductId(Integer id) {
        recipeProductRepository.deleteByRecipeId(id);
    }

    private double calculateIngredientPrice(double productWeight,String weightUnit, double productPrice, String ingredientUnit ,double quantity){
       double weightToGram = convertToGram(weightUnit, productWeight);
       double quantityToGram = convertToGram(ingredientUnit,quantity);
       return (quantityToGram * productPrice)/weightToGram;
    }

    private double convertToGram(String unit, double value){
        return switch (unit) {
            case "g" -> value; // 1 g = 1 g
            case "mg" -> value * 0.001; // 1 mg = 0.001 g
            case "L" -> value * 1000;
            case "kg" -> value * 1000; // 1 kg = 1000 g
            case "ml" -> value; // Przyjmujemy 1 l = 1000 g
            case "pack" -> value * 500; // Zakładamy 1 pakiet = 500 g
            case "can" -> value * 400; // Zakładamy 1 puszka = 400 g
            case "bottle" -> value * 1500; // Zakładamy 1 butelka = 1500 g
            case "jar" -> value * 1000; // Zakładamy 1 słoik = 1000 g
            case "carton" -> value * 1000; // Zakładamy 1 karton = 1000 g
            case "crate" -> value * 10000; // Zakładamy 1 skrzynka = 10,000 g
            case "barrel" -> value * 159000; // Zakładamy 1 baryłka = 159,000 g
            default -> throw new IllegalArgumentException("Nieznana jednostka: " + unit);
        };
    }


}
