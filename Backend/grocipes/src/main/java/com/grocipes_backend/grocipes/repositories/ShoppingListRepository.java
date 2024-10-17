package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

}
