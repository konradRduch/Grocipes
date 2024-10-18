package com.grocipes_backend.grocipes.models.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingListDTO {
    private Integer id;
    private String name;
    private LocalDateTime shopping_date;
    private Integer cardColor;

    private List<ProductShoppingListDTO> productShoppingLists;

    public ShoppingListDTO(Integer id, String name, LocalDateTime shopping_date, Integer cardColor, List<ProductShoppingListDTO> productShoppingLists) {
        this.id = id;
        this.name = name;
        this.shopping_date = shopping_date;
        this.cardColor = cardColor;
        this.productShoppingLists = productShoppingLists;
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

    public LocalDateTime getShopping_date() {
        return shopping_date;
    }

    public void setShopping_date(LocalDateTime shopping_date) {
        this.shopping_date = shopping_date;
    }

    public List<ProductShoppingListDTO> getProductShoppingLists() {
        return productShoppingLists;
    }

    public void setProductShoppingLists(List<ProductShoppingListDTO> productShoppingLists) {
        this.productShoppingLists = productShoppingLists;
    }

    public Integer getCardColor() {
        return cardColor;
    }

    public void setCardColor(Integer cardColor) {
        this.cardColor = cardColor;
    }
}
