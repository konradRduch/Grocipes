package com.grocipes_backend.grocipes.models.DTO;

public class NutritionFactNutrientDTO {

    private Integer productId;
    private Integer nutrientId;
    private double amount;
    private double dailyValue;

    public NutritionFactNutrientDTO(Integer productId, Integer nutrientId, double amount, double dailyValue) {
        this.productId = productId;
        this.nutrientId = nutrientId;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }

    public NutritionFactNutrientDTO(Integer nutrientId, double amount, double dailyValue) {
        this.nutrientId = nutrientId;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }

    public NutritionFactNutrientDTO() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Integer nutrientId) {
        this.nutrientId = nutrientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(double dailyValue) {
        this.dailyValue = dailyValue;
    }
}
