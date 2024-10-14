package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.UnitDailyDemand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitDailyDemandRepository extends JpaRepository<UnitDailyDemand, Integer> {
}
