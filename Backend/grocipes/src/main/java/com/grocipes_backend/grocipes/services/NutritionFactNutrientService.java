package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.NutritionFactNutrientDTO;
import com.grocipes_backend.grocipes.models.Nutrient;
import com.grocipes_backend.grocipes.models.NutritionFactNutrient;
import com.grocipes_backend.grocipes.models.NutritionFactNutrientId;
import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.repositories.NutrientRepository;
import com.grocipes_backend.grocipes.repositories.NutritionFactNutrientRepository;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NutritionFactNutrientService {
    private final NutritionFactNutrientRepository nutritionFactNutrientRepository;
    private final NutrientRepository nutrientRepository;
    private final ProductRepository productRepository;

    public NutritionFactNutrientService(NutritionFactNutrientRepository nutritionFactNutrientRepository,
                                        NutrientRepository nutrientRepository,
                                        ProductRepository productRepository) {
        this.nutritionFactNutrientRepository = nutritionFactNutrientRepository;
        this.nutrientRepository = nutrientRepository;
        this.productRepository = productRepository;
    }

    public Optional<NutritionFactNutrient> addNutrionFactNutrient(NutritionFactNutrientDTO dto) {
        // Sprawdź, czy nutrient i product są poprawnie przypisane
        Nutrient nutrient = nutrientRepository.findById(dto.getNutrientId())
                .orElseThrow(() -> new RuntimeException("Nutrient not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        NutritionFactNutrientId id = new NutritionFactNutrientId();
        id.setProductId(product.getId()); // Zakładając, że product nie jest nullem i ma ustawione ID
        id.setNutrientId(nutrient.getId());

        NutritionFactNutrient entity = new NutritionFactNutrient(id,product, nutrient,dto.getAmount(), dto.getDailyValue());

        return Optional.of(nutritionFactNutrientRepository.save(entity));
    }



}
