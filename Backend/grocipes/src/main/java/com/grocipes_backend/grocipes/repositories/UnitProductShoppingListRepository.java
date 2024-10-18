package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.UnitProductShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitProductShoppingListRepository extends JpaRepository<UnitProductShoppingList, Integer> {

    UnitProductShoppingList findUnitProductShoppingListById(Integer id);
}
