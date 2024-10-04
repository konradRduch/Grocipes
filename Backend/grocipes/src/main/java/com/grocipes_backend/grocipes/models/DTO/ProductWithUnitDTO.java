package com.grocipes_backend.grocipes.models.DTO;

public class ProductWithUnitDTO {

    private String name;
    private double weight;
    private double price;
    private String image_url;
    private Integer calories;
    private String unitName;
    private double unitValue;

    public ProductWithUnitDTO(String name, double weight, double price, String image_url, Integer calories, String unitName, double unitValue) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
        this.unitName = unitName;
        this.unitValue = unitValue;
    }

    public ProductWithUnitDTO() {
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }
}
