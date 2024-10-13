package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

@Entity
public class ProductShoppingList {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double quantity;
    private boolean done;
    @ManyToOne
    @JoinColumn(name="shopping_list_id")
    private ShoppingList shoppingList;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="unit_product_shopping_list_id")
    private UnitProductShoppingList unitProductShoppingList;

    public ProductShoppingList() {
    }

    public ProductShoppingList(Integer id, double quantity, boolean done, ShoppingList shoppingList, Product product, UnitProductShoppingList unitProductShoppingList) {
        this.id = id;
        this.quantity = quantity;
        this.done = done;
        this.shoppingList = shoppingList;
        this.product = product;
        this.unitProductShoppingList = unitProductShoppingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UnitProductShoppingList getUnitProductShoppingList() {
        return unitProductShoppingList;
    }

    public void setUnitProductShoppingList(UnitProductShoppingList unitProductShoppingList) {
        this.unitProductShoppingList = unitProductShoppingList;
    }
}
