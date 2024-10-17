package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public void save(ShoppingList shoppingList){
        shoppingListRepository.save(shoppingList);
    }


}
