package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.DailyDemand;
import com.grocipes_backend.grocipes.models.Nutrient;
import com.grocipes_backend.grocipes.models.NutritionFactNutrientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyDemandRepository extends JpaRepository<DailyDemand, Integer> {

    void deleteDailyDemandByNutritionalGoalId(Integer id);

    List<DailyDemand>findDailyDemandByNutritionalGoalId(Integer nutritionalGoal_id);



}
