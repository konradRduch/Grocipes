package com.grocipes_backend.grocipes.models.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingListCreationDTO {
    private Integer userId;
    private String name;
    private LocalDateTime shoppingDate;
    private Integer colorCard;
    private List<ProductShoppingListCreationDTO> products;

    public ShoppingListCreationDTO(Integer userId, String name, LocalDateTime shoppingDate, Integer colorCard, List<ProductShoppingListCreationDTO> products) {
        this.userId = userId;
        this.name = name;
        this.shoppingDate = shoppingDate;
        this.colorCard = colorCard;
        this.products = products;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(LocalDateTime shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public Integer getColorCard() {
        return colorCard;
    }

    public void setColorCard(Integer colorCard) {
        this.colorCard = colorCard;
    }

    public List<ProductShoppingListCreationDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductShoppingListCreationDTO> products) {
        this.products = products;
    }
}
