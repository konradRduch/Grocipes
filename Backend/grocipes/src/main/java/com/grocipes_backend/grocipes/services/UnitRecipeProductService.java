package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.UnitRecipeProduct;
import com.grocipes_backend.grocipes.repositories.UnitRecipeProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitRecipeProductService {
    private final UnitRecipeProductRepository unitRecipeProductRepository;

    public UnitRecipeProductService(UnitRecipeProductRepository unitRecipeProductRepository) {
        this.unitRecipeProductRepository = unitRecipeProductRepository;
    }

    public Optional<UnitRecipeProduct> addUnit(UnitRecipeProduct unit){
        return Optional.of(unitRecipeProductRepository.save(unit));
    }



}
