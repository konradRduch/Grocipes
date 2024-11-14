package com.grocipes_backend.grocipes.models.DTO;

import java.util.List;

public class GetRecipesDTO {
    private Integer id;
    private String title;
    private String description;
    private String preparation_method;
    private String image_url;
    private String typeOfMeal;
    List<ProductWithUnitDTO> products;

    public GetRecipesDTO() {
    }

    public GetRecipesDTO(Integer id, String title, String description, String preparation_method, String image_url,String typeOfMeal, List<ProductWithUnitDTO> products) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
        this.typeOfMeal = typeOfMeal;
        this.products = products;
    }

    public GetRecipesDTO(Integer id, String title, String description, String preparation_method, String image_url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
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

    public List<ProductWithUnitDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithUnitDTO> products) {
        this.products = products;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }
}
