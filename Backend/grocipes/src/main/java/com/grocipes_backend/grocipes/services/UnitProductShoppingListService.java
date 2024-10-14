package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.UnitProductShoppingList;
import com.grocipes_backend.grocipes.repositories.UnitProductShoppingListRepository;
import com.grocipes_backend.grocipes.repositories.UnitRecipeProductRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitProductShoppingListService {
   private final UnitProductShoppingListRepository unitProductShoppingListRepository;

    public UnitProductShoppingListService(UnitProductShoppingListRepository unitProductShoppingListRepository) {
        this.unitProductShoppingListRepository = unitProductShoppingListRepository;
    }
}
