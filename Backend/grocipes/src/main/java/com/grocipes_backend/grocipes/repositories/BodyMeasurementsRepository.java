package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.BodyMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyMeasurementsRepository extends JpaRepository<BodyMeasurements, Integer> {
}
