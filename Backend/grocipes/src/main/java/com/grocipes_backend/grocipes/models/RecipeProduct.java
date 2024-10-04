package com.grocipes_backend.grocipes.models;
import jakarta.persistence.*;

@Entity
public class RecipeProduct {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private UnitRecipeProduct unit;


    public RecipeProduct() {
    }

    public RecipeProduct(double quantity, Recipe recipe, Product product, UnitRecipeProduct unit) {
        this.quantity = quantity;
        this.recipe = recipe;
        this.product = product;
        this.unit = unit;
    }

    public RecipeProduct(double quantity) {
        this.quantity = quantity;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UnitRecipeProduct getUnit() {
        return unit;
    }

    public void setUnit(UnitRecipeProduct unit) {
        this.unit = unit;
    }
}
