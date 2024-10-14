package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.EatDeadlineRepository;
import org.springframework.stereotype.Service;

@Service
public class EatDeadlineService {

    private final EatDeadlineRepository eatDeadlineRepository;

    public EatDeadlineService(EatDeadlineRepository eatDeadlineRepository) {
        this.eatDeadlineRepository = eatDeadlineRepository;
    }
}
