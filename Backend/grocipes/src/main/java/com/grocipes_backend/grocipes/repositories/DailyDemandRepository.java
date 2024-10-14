package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.DailyDemand;
import com.grocipes_backend.grocipes.models.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyDemandRepository extends JpaRepository<DailyDemand, Integer> {
}
