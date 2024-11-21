package com.grocipes_backend.grocipes.models.DTO;

import java.util.List;

public class GetProductsDTO {
    private Integer id;
    private String name;
    private double weight;
    private double price;
    private String image_url;
    private Integer calories;
    private Integer unitId;
    private List<NutrientDTO> nutrient;

    public GetProductsDTO(Integer id, String name, double weight, double price, String image_url, Integer calories, List<NutrientDTO> nutrient,Integer unitId) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
        this.nutrient = nutrient;
        this.unitId = unitId;
    }

    public GetProductsDTO(Integer id, String name, double weight, double price, String image_url, Integer calories) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
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

    public List<NutrientDTO> getNutrient() {
        return nutrient;
    }

    public void setNutrient(List<NutrientDTO> nutrient) {
        this.nutrient = nutrient;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }
}
