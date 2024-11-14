package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.NutritionSchedule;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NutritionScheduleRepository extends JpaRepository<NutritionSchedule, Integer> {
    NutritionSchedule findNutritionScheduleByUserEntityId(Integer userId);
    @Query(value = "SELECT * FROM nutrition_schedule WHERE user_id = :user_id", nativeQuery = true)
    List<NutritionSchedule> findByUserId(@Param("user_id") Integer userId);
}
