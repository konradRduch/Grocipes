package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.ProductShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {
    ShoppingList findShoppingListById(Integer id);
    @Query(
            "SELECT (p.id,p.calories,p.image_url,p.name,p.price,p.weight, psl.id,psl.done,psl.quantity, sl.id,sl.name,sl.shopping_date,sl.cardColor, upsl.id,upsl.name) " +
            "FROM ProductShoppingList psl " +
            "JOIN Product p ON p.id = psl.product.id " +
            "JOIN ShoppingList sl ON sl.id = psl.shoppingList.id " +
            "JOIN UnitProductShoppingList upsl ON upsl.id = psl.unitProductShoppingList.id "

    )
    List<Object[]> findShoppingLists();

    @Query(
            "SELECT (sl, ss) " +
                    "FROM ShoppingList sl " +
                    "JOIN ShoppingSchedule ss ON ss.id = sl.shoppingList.id"

    )
    List<Object[]> findShoppingListsWithProductsByUserId(@Param("userId") Integer userId);

    List<ShoppingList> findShoppingListByShoppingList(ShoppingSchedule shoppingSchedule);
}
