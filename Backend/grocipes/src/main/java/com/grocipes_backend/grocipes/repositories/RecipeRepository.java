package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByTitle(String title);

    Recipe findRecipeById(Integer id);
    boolean existsByTitle(String title);

    @Query("SELECT (r.id,r.title,r.description,r.preparation_method,r.image_url, r.typeOfMeal ,p.id,p.name,p.weight, rp.price,p.image_url,p.calories,rp.quantity,urp.id, urp.name) " +
            "FROM RecipeProduct rp " +
            "JOIN Recipe r ON r.id = rp.recipe.id " +
            "JOIN Product p ON p.id = rp.product.id " +
            "JOIN UnitRecipeProduct urp ON urp.id = rp.unit.id "
    )
    List<Object[]>findRecipes();

}
