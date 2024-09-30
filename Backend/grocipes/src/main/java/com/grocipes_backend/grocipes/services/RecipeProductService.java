package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.RecipeProductRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeProductService {
    private final RecipeProductRepository recipeProductRepository;

    public RecipeProductService(RecipeProductRepository recipeProductRepository) {
        this.recipeProductRepository = recipeProductRepository;
    }

}
