package com.grocipes_backend.grocipes.models.DTO;

public class NutritionFactNutrientDTO {

    private Integer productId;
    private Integer nutrientId;
    private Integer amount;
    private Integer dailyValue;

    public NutritionFactNutrientDTO(Integer productId, Integer nutrientId, Integer amount, Integer dailyValue) {
        this.productId = productId;
        this.nutrientId = nutrientId;
        this.amount = amount;
        this.dailyValue = dailyValue;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(Integer dailyValue) {
        this.dailyValue = dailyValue;
    }
}
