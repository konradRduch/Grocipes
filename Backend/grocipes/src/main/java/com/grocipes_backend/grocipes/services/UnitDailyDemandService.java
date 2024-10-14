package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.UnitDailyDemandRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitDailyDemandService {

    private final UnitDailyDemandRepository unitDailyDemandRepository;

    public UnitDailyDemandService(UnitDailyDemandRepository unitDailyDemandRepository) {
        this.unitDailyDemandRepository = unitDailyDemandRepository;
    }
}
