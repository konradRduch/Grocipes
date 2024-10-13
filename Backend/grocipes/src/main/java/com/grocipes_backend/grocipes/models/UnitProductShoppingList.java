package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UnitProductShoppingList {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "unitProductShoppingList", cascade = CascadeType.ALL)
    private List<ProductShoppingList> productShoppingLists;

    public UnitProductShoppingList() {
    }

    public UnitProductShoppingList(Integer id, String name, List<ProductShoppingList> productShoppingLists) {
        this.id = id;
        this.name = name;
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

    public List<ProductShoppingList> getProductShoppingLists() {
        return productShoppingLists;
    }

    public void setProductShoppingLists(List<ProductShoppingList> productShoppingLists) {
        this.productShoppingLists = productShoppingLists;
    }
}
