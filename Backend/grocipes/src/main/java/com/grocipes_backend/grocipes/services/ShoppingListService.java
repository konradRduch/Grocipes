package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public void save(ShoppingList shoppingList){
        shoppingListRepository.save(shoppingList);
    }


    public List<Object[]> getAllByUserId(Integer userId) {
        List<Object[]> xd = shoppingListRepository.findShoppingListsWithProductsByUserId(userId);
        return shoppingListRepository.findShoppingListsWithProductsByUserId(userId);
    }
    public List<ShoppingList> findBySchoppingSchedule(ShoppingSchedule shoppingSchedule){
        return shoppingListRepository.findShoppingListByShoppingList(shoppingSchedule);
    }
}
