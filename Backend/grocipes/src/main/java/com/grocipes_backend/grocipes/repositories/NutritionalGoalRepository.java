package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.NutritionalGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NutritionalGoalRepository extends JpaRepository<NutritionalGoal, Integer> {
    NutritionalGoal findNutritionalGoalById(Integer id);
    void deleteById(Integer id);
    List<NutritionalGoal>findNutritionalGoalsByUserEntityId(Integer userId);

}
