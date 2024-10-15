package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.BodyMeasurementsRepository;
import org.springframework.stereotype.Service;

@Service
public class BodyMeasurementsService {
    private final BodyMeasurementsRepository bodyMeasurementsRepository;

    public BodyMeasurementsService(BodyMeasurementsRepository bodyMeasurementsRepository) {
        this.bodyMeasurementsRepository = bodyMeasurementsRepository;
    }

    

}
