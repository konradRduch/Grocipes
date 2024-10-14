package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.DailyDemandRepository;
import org.springframework.stereotype.Service;

@Service
public class DailyDemandService {
    private final DailyDemandRepository demandRepository;

    public DailyDemandService(DailyDemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }
}
