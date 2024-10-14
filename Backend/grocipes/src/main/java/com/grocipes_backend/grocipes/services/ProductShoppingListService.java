package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.repositories.ProductShoppingListRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductShoppingListService {
    private final ProductShoppingListRepository productShoppingListRepository;

    public ProductShoppingListService(ProductShoppingListRepository productShoppingListRepository) {
        this.productShoppingListRepository = productShoppingListRepository;
    }
}
