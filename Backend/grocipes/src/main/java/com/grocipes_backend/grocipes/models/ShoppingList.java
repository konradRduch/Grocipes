package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ShoppingList {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer cardColor;
    private LocalDateTime shopping_date;
    private boolean likedList;

    @ManyToOne
    @JoinColumn(name="shopping_schedule_id")
    private ShoppingSchedule shoppingList;
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    private List<ProductShoppingList>productShoppingLists;
    public ShoppingList() {
    }

    public ShoppingList(Integer id, String name, Integer cardColor, LocalDateTime shopping_date, boolean likedList, ShoppingSchedule shoppingList, List<ProductShoppingList> productShoppingLists) {
        this.id = id;
        this.name = name;
        this.cardColor = cardColor;
        this.shopping_date = shopping_date;
        this.likedList = likedList;
        this.shoppingList = shoppingList;
        this.productShoppingLists = productShoppingLists;
    }

    public boolean isLikedList() {
        return likedList;
    }

    public void setLikedList(boolean likedList) {
        this.likedList = likedList;
    }

    public Integer getCardColor() {
        return cardColor;
    }

    public void setCardColor(Integer cardColor) {
        this.cardColor = cardColor;
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

    public ShoppingSchedule getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingSchedule shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<ProductShoppingList> getProductShoppingLists() {
        return productShoppingLists;
    }

    public void setProductShoppingLists(List<ProductShoppingList> productShoppingLists) {
        this.productShoppingLists = productShoppingLists;
    }
}
