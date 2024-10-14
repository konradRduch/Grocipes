package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.ProductShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductShoppingListRepository extends JpaRepository<ProductShoppingList, Integer> {
}
