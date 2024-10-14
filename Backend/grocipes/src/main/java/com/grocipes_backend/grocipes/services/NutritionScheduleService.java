package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.NutritionScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class NutritionScheduleService {
    private final NutritionScheduleRepository nutritionScheduleRepository;

    public NutritionScheduleService(NutritionScheduleRepository nutritionScheduleRepository) {
        this.nutritionScheduleRepository = nutritionScheduleRepository;
    }
}
