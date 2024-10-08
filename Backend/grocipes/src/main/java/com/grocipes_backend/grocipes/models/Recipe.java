package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(columnDefinition="varchar(1000)")
    private String description;
    @Column(columnDefinition="varchar(1000)")
    private String preparation_method;
    private String image_url;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeProduct> recipeProducts = new ArrayList<>();
    public Recipe() {
    }

    public Recipe(String title, String description, String preparation_method, String image_url) {
        this.title = title;
        this.description = description;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
    }

    public Recipe(Integer id, String title, String description, String preparation_method, String image_url, List<RecipeProduct> recipeProducts) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
        this.recipeProducts = recipeProducts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparation_method() {
        return preparation_method;
    }

    public void setPreparation_method(String preparation_method) {
        this.preparation_method = preparation_method;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<RecipeProduct> getRecipeProducts() {
        return recipeProducts;
    }

    public void setRecipeProducts(List<RecipeProduct> recipeProducts) {
        this.recipeProducts = recipeProducts;
    }

}
