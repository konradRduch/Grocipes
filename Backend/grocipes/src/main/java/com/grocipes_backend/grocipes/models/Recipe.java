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
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<EatDeadline>eatDeadlines;

    @ManyToMany(mappedBy = "favouriteRecipes")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Image>images;
    public Recipe() {
    }

    public Recipe(String title, String description, String preparation_method, String image_url) {
        this.title = title;
        this.description = description;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
    }

    public Recipe(Integer id, String title, String description, String preparation_method, String image_url, List<RecipeProduct> recipeProducts, List<EatDeadline> eatDeadlines, List<UserEntity> users, List<Image> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
        this.recipeProducts = recipeProducts;
        this.eatDeadlines = eatDeadlines;
        this.users = users;
        this.images = images;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<EatDeadline> getEatDeadlines() {
        return eatDeadlines;
    }

    public void setEatDeadlines(List<EatDeadline> eatDeadlines) {
        this.eatDeadlines = eatDeadlines;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
