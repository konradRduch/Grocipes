package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientRepository extends JpaRepository<Nutrient, Integer> {
}
