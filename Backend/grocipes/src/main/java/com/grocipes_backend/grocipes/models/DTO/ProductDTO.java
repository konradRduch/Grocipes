package com.grocipes_backend.grocipes.models.DTO;

public class ProductDTO {
    private String name;
    private double weight;
    private double price;
    private String image_url;
    private Integer calories;

    public ProductDTO(String name, double weight, double price, String image_url, Integer calories) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
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
}
