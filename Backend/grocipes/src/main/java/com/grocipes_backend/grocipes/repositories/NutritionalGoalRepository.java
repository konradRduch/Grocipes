package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.NutritionalGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionalGoalRepository extends JpaRepository<NutritionalGoal, Integer> {
}
