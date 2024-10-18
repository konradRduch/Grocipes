package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.ProductShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductShoppingListRepository extends JpaRepository<ProductShoppingList, Integer> {
    List<ProductShoppingList> findProductShoppingListByShoppingList(ShoppingList shoppingList);
}
