package com.grocipes_backend.grocipes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double weight;
    private double price;
    private String image_url;
    private Integer calories;


    // Relacja z NutritionFactNutrient
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<NutritionFactNutrient> nutritionFacts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<RecipeProduct> recipeProducts = new ArrayList<>();

    public Product() {
    }

    public Product(Integer id, String name, double weight, double price, String image_url, Integer calories) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
    }

    public Product(String name, double weight, double price, String image_url, Integer calories) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
    }

    public Product(Integer id, String name, double weight, double price, String image_url, Integer calories, List<NutritionFactNutrient> nutritionFacts, List<RecipeProduct> recipeProducts) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
        this.nutritionFacts = nutritionFacts;
        this.recipeProducts = recipeProducts;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public List<NutritionFactNutrient> getNutritionFacts() {
        return nutritionFacts;
    }

    public void setNutritionFacts(List<NutritionFactNutrient> nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
    }

    public List<RecipeProduct> getRecipeProducts() {
        return recipeProducts;
    }

    public void setRecipeProducts(List<RecipeProduct> recipeProducts) {
        this.recipeProducts = recipeProducts;
    }
}
