package com.grocipes_backend.grocipes.models.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class EditShoppingListDTO {
    private Integer id;
    private String name;
    private LocalDateTime shopping_date;
    private Integer cardColor;
    private boolean likedList;
    private List<ProductShoppingListCreationDTO> productShoppingLists;

    public EditShoppingListDTO() {
    }

    public EditShoppingListDTO(Integer id, String name, LocalDateTime shopping_date, Integer cardColor, boolean likedList, List<ProductShoppingListCreationDTO> productShoppingLists) {
        this.id = id;
        this.name = name;
        this.shopping_date = shopping_date;
        this.cardColor = cardColor;
        this.likedList = likedList;
        this.productShoppingLists = productShoppingLists;
    }

    public boolean isLikedList() {
        return likedList;
    }

    public void setLikedList(boolean likedList) {
        this.likedList = likedList;
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

    public Integer getCardColor() {
        return cardColor;
    }

    public void setCardColor(Integer cardColor) {
        this.cardColor = cardColor;
    }

    public List<ProductShoppingListCreationDTO> getProductShoppingLists() {
        return productShoppingLists;
    }

    public void setProductShoppingLists(List<ProductShoppingListCreationDTO> productShoppingLists) {
        this.productShoppingLists = productShoppingLists;
    }
}
