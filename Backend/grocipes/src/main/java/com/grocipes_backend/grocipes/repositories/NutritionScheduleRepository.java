package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.NutritionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionScheduleRepository extends JpaRepository<NutritionSchedule, Integer> {
}
