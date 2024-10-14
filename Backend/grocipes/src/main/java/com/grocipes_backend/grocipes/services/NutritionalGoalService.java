package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.NutritionalGoalRepository;
import org.springframework.stereotype.Service;

@Service
public class NutritionalGoalService {

    private final NutritionalGoalRepository nutritionalGoalRepository;

    public NutritionalGoalService(NutritionalGoalRepository nutritionalGoalRepository) {
        this.nutritionalGoalRepository = nutritionalGoalRepository;
    }
}
