package com.grocipes_backend.grocipes.models.DTO;

import java.util.List;

public class RecipeCreationDTO {
    private RecipeDTO recipe;

    private List<RecipeProductDTO> products;

    public RecipeCreationDTO(RecipeDTO recipe, List<RecipeProductDTO> products) {
        this.recipe = recipe;
        this.products = products;
    }

    public RecipeDTO getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeDTO recipe) {
        this.recipe = recipe;
    }

    public List<RecipeProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<RecipeProductDTO> products) {
        this.products = products;
    }
}
