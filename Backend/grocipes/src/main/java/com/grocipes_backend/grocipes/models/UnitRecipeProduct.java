package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UnitRecipeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "unit")
    private List<RecipeProduct> recipeProducts;

    @OneToMany(mappedBy = "unit")
    private List<Product> products;

    public UnitRecipeProduct() {
    }

    public UnitRecipeProduct(Integer id, String name, List<RecipeProduct> recipeProducts) {
        this.id = id;
        this.name = name;
        this.recipeProducts = recipeProducts;
    }

    public UnitRecipeProduct(Integer id, String name, List<RecipeProduct> recipeProducts, List<Product> products) {
        this.id = id;
        this.name = name;
        this.recipeProducts = recipeProducts;
        this.products = products;
    }

    public UnitRecipeProduct(String name) {
        this.name = name;
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

    public List<RecipeProduct> getRecipeProducts() {
        return recipeProducts;
    }

    public void setRecipeProducts(List<RecipeProduct> recipeProducts) {
        this.recipeProducts = recipeProducts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
