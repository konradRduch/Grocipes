package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.DTO.NutritionFactNutrientDTO;
import com.grocipes_backend.grocipes.models.NutritionFactNutrient;
import com.grocipes_backend.grocipes.models.NutritionFactNutrientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NutritionFactNutrientRepository extends JpaRepository<NutritionFactNutrient,NutritionFactNutrientId> {
    void deleteByProductId(Integer productId);

    @Query("SELECT n FROM NutritionFactNutrient n WHERE n.id.nutrientId = :nutrientId")
    List<NutritionFactNutrient> findByNutrientId(@Param("nutrientId") Integer nutrientId);

}
