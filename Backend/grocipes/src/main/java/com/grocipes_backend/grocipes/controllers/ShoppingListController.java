package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.ShoppingListCreationDTO;
import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.services.ProductShoppingListService;
import com.grocipes_backend.grocipes.services.ShoppingListService;
import com.grocipes_backend.grocipes.services.ShoppingScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingListController {

    private ShoppingListService shoppingListService;
    private ShoppingScheduleService shoppingScheduleService;
    private ProductShoppingListService productShoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService, ShoppingScheduleService shoppingScheduleService, ProductShoppingListService productShoppingListService) {
        this.shoppingListService = shoppingListService;
        this.shoppingScheduleService = shoppingScheduleService;
        this.productShoppingListService = productShoppingListService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addShoppingList(@RequestBody ShoppingListCreationDTO request){
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(request.getName());
        shoppingList.setShopping_date(request.getShoppingDate());
        shoppingList.setCardColor(request.getColorCard());
        ShoppingSchedule shoppingSchedule = shoppingScheduleService.findShoppingScheduleByUserId(request.getUserId());
        shoppingList.setShoppingList(shoppingSchedule);
        shoppingListService.save(shoppingList);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addProductToShoppingList")
    public ResponseEntity<Void> addProductToShoppingList(){


        return ResponseEntity.ok().build();
    }




}
