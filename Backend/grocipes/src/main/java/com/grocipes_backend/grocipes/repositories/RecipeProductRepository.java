package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.RecipeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeProductRepository extends JpaRepository<RecipeProduct, Integer> {
    void deleteByRecipeId(Integer id);

    List<RecipeProduct> findRecipeProductsByRecipeId(Integer recipeId);

}
    