package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.RecipeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeProductRepository extends JpaRepository<RecipeProduct, Integer> {
}
