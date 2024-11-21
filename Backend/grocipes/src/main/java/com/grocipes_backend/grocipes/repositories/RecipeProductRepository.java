package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.RecipeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeProductRepository extends JpaRepository<RecipeProduct, Integer> {
    void deleteByRecipeId(Integer id);

    List<RecipeProduct> findRecipeProductsByRecipeId(Integer recipeId);

    @Query("SELECT SUM(rp.price) FROM RecipeProduct rp WHERE rp.recipe.id = :recipeId")
    Double findRecipeProductsByRecipeIdAndAggregate(@Param("recipeId") Integer recipeId);

}
    