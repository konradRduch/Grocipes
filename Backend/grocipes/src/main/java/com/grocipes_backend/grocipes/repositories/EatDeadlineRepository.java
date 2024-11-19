package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.DTO.EatDeadlineDTO;
import com.grocipes_backend.grocipes.models.EatDeadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EatDeadlineRepository extends JpaRepository<EatDeadline,Integer> {

    @Query("SELECT new com.grocipes_backend.grocipes.models.DTO.EatDeadlineDTO(ed.id,r.id,r.title, r.typeOfMeal,ed.eating_date,ed.done,ed.rate,ed.comment) " +
            "FROM EatDeadline ed " +
            "JOIN Recipe r ON ed.recipe.id = r.id "+
            "JOIN NutritionSchedule ns ON ns.id = ed.nutritionSchedule.id "+
            "WHERE ns.userEntity.id = :userId"
    )
    List<EatDeadlineDTO> findAllEatDeadline(@Param("userId") Integer userId);


}
