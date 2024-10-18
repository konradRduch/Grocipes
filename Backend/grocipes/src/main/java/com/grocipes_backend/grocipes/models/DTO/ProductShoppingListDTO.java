package com.grocipes_backend.grocipes.models.DTO;

public class ProductShoppingListDTO {
    private Integer id;
    private Integer productId;
    private String name;
    private double weight;
    private double price;
    private String image_url;
    private Integer calories;
    private Integer shopListId;
    private double quantity;
    private Integer unitId;
    private String unitName;
    private Boolean done;

    public ProductShoppingListDTO(Integer id, Integer productId, String name, double weight, double price, String image_url, Integer calories, Integer shopListId, double quantity, Integer unitId, String unitName, Boolean done) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.image_url = image_url;
        this.calories = calories;
        this.shopListId = shopListId;
        this.quantity = quantity;
        this.unitId = unitId;
        this.unitName = unitName;
        this.done = done;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }



    public Integer getShopListId() {
        return shopListId;
    }

    public void setShopListId(Integer shopListId) {
        this.shopListId = shopListId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
