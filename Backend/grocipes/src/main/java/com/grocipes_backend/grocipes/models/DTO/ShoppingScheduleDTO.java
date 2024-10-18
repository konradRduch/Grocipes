package com.grocipes_backend.grocipes.models.DTO;

import java.util.List;

public class ShoppingScheduleDTO {
    private Integer id;
    private String name;
    private List<ShoppingListDTO>shoppingList;
    private Integer userId;

    public ShoppingScheduleDTO(Integer id, String name, List<ShoppingListDTO> shoppingList, Integer userId) {
        this.id = id;
        this.name = name;
        this.shoppingList = shoppingList;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingListDTO> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingListDTO> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
