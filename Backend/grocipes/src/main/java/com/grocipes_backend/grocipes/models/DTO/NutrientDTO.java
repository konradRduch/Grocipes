package com.grocipes_backend.grocipes.models.DTO;

public class NutrientDTO {

    private String name;
    private Integer amount;
    private Integer dailyValue;

    public NutrientDTO(String name, Integer amount, Integer dailyValue) {
        this.name = name;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
