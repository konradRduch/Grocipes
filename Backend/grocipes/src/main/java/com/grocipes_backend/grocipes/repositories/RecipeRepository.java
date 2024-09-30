package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByTitle(String title);


}
