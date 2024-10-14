package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.ShoppingScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingScheduleService {

    private final ShoppingScheduleRepository shoppingScheduleRepository;

    public ShoppingScheduleService(ShoppingScheduleRepository shoppingScheduleRepository) {
        this.shoppingScheduleRepository = shoppingScheduleRepository;
    }
}
