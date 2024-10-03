package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.Nutrient;
import com.grocipes_backend.grocipes.repositories.NutrientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NutrientService {
    private final NutrientRepository nutrientRepository;

    public NutrientService(NutrientRepository nutrientRepository) {
        this.nutrientRepository = nutrientRepository;
    }

    public List<Nutrient>getAllNutrients(){
        return nutrientRepository.findAll();
    }
    public Optional<Nutrient> addNutrient(Nutrient nutrient){
        return Optional.of(nutrientRepository.save(nutrient));
    }

}
