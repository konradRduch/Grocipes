package com.grocipes_backend.grocipes.models.DTO;

public class RecipeProductDTO {
    private Integer unit_id;
    private Integer recipe_id;
    private Integer product_id;
    private double quantity;

    public RecipeProductDTO() {
    }

    public RecipeProductDTO(Integer unit_id, Integer recipe_id, Integer product_id, double quantity) {
        this.unit_id = unit_id;
        this.recipe_id = recipe_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public RecipeProductDTO(Integer unit_id, Integer product_id, double quantity) {
        this.unit_id = unit_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Integer getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Integer unit_id) {
        this.unit_id = unit_id;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
