package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.UnitRecipeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRecipeProductRepository extends JpaRepository<UnitRecipeProduct, Integer> {
}
