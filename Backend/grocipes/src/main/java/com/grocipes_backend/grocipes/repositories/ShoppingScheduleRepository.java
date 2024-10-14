package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingScheduleRepository extends JpaRepository<ShoppingSchedule, Integer> {
}
